/*
 * Copyright (C) 2006 Joe Walnes.
 * Copyright (C) 2006, 2007 XStream Committers.
 * All rights reserved.
 *
 * The software in this package is published under the terms of the BSD
 * style license a copy of which has been included with this distribution in
 * the LICENSE.txt file.
 * 
 * Created on 06. March 2004 by Mauro Talevi
 */
package com.thoughtworks.xstream.core;

import java.util.BitSet;
import java.io.ByteArrayInputStream;

import junit.framework.TestCase;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.mapper.CannotResolveClassException;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.SingleValueConverterWrapper;
import com.thoughtworks.xstream.converters.ConversionException;
import com.thoughtworks.xstream.converters.basic.StringConverter;
import com.thoughtworks.xstream.converters.collections.BitSetConverter;

/**
 * @author Guilherme Silveira
 */
public class DefaultConverterLookupTest extends TestCase {

	public void testCanReplaceWithHigherPriority() {
		
		// this test actually depends on the keyset implementation of the corresponding cache map.
		final DefaultConverterLookup lookup = new DefaultConverterLookup();
		Converter currentConverter = new SingleValueConverterWrapper(new StringConverter());
		lookup.registerConverter(new BitSetConverter(), XStream.PRIORITY_VERY_HIGH);
		lookup.registerConverter(currentConverter, -100);
		lookup.lookupConverterForType(String.class);
		lookup.lookupConverterForType(BitSet.class);
		assertEquals(lookup.lookupConverterForType(String.class), currentConverter);
		Converter newConverter = new SingleValueConverterWrapper(new StringConverter());
		lookup.registerConverter(newConverter, 100);
		assertEquals(lookup.lookupConverterForType(String.class), newConverter);
	}

    /**
     * Caching the failure should function correctly.
     * 
     */
    public void testFailureCache() {
        String xml = "<root><field class='NoSuchClass'/></root>";
        XStream xs = new XStream();
        xs.alias("root",Root.class);
        for (int i=0; i<3; i++) {
            try {
                xs.fromXML(new ByteArrayInputStream(xml.getBytes()));
                fail(); // should fail to unmarshal
            } catch (ConversionException e) {
                assertTrue(e.getCause() instanceof CannotResolveClassException);
            }
        }
    }

    public static class Root {
        public Object field;
    }
}

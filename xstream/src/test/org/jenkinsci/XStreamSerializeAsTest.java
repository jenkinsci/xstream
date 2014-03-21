package org.jenkinsci;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamSerializeAs;
import com.thoughtworks.xstream.mapper.AnnotationMapper;
import com.thoughtworks.xstream.mapper.MapperWrapper;
import junit.framework.TestCase;

/**
 * @author Kohsuke Kawaguchi
 */
public class XStreamSerializeAsTest extends TestCase {
    private XStream xstream = new XStream() {
        @Override
        protected MapperWrapper wrapMapper(MapperWrapper next) {
            return new AnnotationMapper(next, getConverterRegistry(), getConverterLookup(), getClassLoaderReference(), getReflectionProvider());
        }
    };

    public void test1() throws Exception {
        xstream.processAnnotations(Bar.class);

        Holder h = new Holder();
        h.foo = new Bar();

        assertEquals(xstream.toXML(h),
                "<org.jenkinsci.XStreamSerializeAsTest_-Holder>\n" +
                "  <foo/>\n" +
                "</org.jenkinsci.XStreamSerializeAsTest_-Holder>");
    }

    public static class Holder {
        Foo foo;
    }

    @XStreamSerializeAs(Foo.class)
    public static class Foo {}
    public static class Bar extends Foo {}

}

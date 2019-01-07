package com.example.lklab.xmltest;

import org.w3c.dom.*;
import java.util.*;

public final class XMLUtil {
    private XMLUtil() {}

    public static List<Node> asList(NodeList n){
        return n.getLength()==0?
                Collections.<Node>emptyList():new NodeListWrapper(n);
    }

    private static class NodeListWrapper extends AbstractList<Node>
            implements RandomAccess {
        private final NodeList list;
        NodeListWrapper(NodeList l){
            list = l;
        }
        @Override
        public Node get(int index) {
            return list.item(index);
        }
        @Override
        public int size() {
            return list.getLength();
        }
    }
}

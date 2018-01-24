package ua.nure.sizonov.Practice2;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyListImpl implements MyList {
//    int i;
    private Object[] obj;
    private static final   Object[] ELEMENT = {};

    public MyListImpl(){
        this.obj = ELEMENT;
    }

    @Override
    public void add(Object e) {
//        Object[] temp = new Object[obj.length+1];
//        for(int i=0; i<obj.length; i++){
//            temp[i]=obj[i];
//        }
//        temp[temp.length-1]=e;
//        this.obj = temp;
        Object[] temp = obj;
        obj = new Object[obj.length + 1];
        System.arraycopy(temp, 0, obj, 0, temp.length);
        obj[obj.length - 1] = e;
    }

    @Override
    public void clear() {
        this.obj = new Object[0];
    }

    @Override
    public boolean remove(Object o) {
        boolean b = false;

        for (int i = 0; i < obj.length; i++) {
            if (o.equals(obj[i])) {

                Object temp[] = obj;
                ///obj[i] = null;
                //remove(obj[i]);
                obj = new Object[obj.length - 1];
                //temp[i]=obj[i];
                System.arraycopy(temp, 0, obj, 0, i);
                System.arraycopy(temp, i + 1, obj, i, obj.length - i);
                b = true;
            }
        }

        return b;
    }

    @Override
    public Object[] toArray() {
        Object[] temp = new Object[obj.length];
        for (int i = 0; i < obj.length; i++) {
            temp[i] = obj[i];
        }

        return temp;
    }

    @Override
    public int size() {
        int s = 0;
        for (int i = 0; i < obj.length; i++)
            s++;

        return s;
    }

    @Override
    public boolean contains(Object o) {
        boolean b = false;
        for (Object o1 : obj) {
            if (o1 == o) {
                b = true;
            }
        }
        return b;
    }

    @Override
    public boolean containsAll(MyList c) {
        int h = 0;

        for (int i = 0; i<c.toArray().length; i++)
            for (int t=0; t<obj.length; t++){
            if (obj[t] == c.toArray()[i])h++;
            if (h==c.size())
                return true;
            }

//        for (Object o : c) {
//            for (Object o1 : obj) {
//                if (o1==o)h++;
//                if (h==c.size())
//                    return true;
//            }
//        }

        return false;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("");
        sb.append("").append(obj == null ? "null" : Arrays.asList(obj).toString());
        return sb.toString();
    }

    @Override
    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {
        int index;

        @Override
        public boolean hasNext() {
            return index < obj.length;
        }

        @Override
        public Object next() {
            if (obj==null)throw new NoSuchElementException();
            if (this.hasNext()) {

                return obj[index++];
            }
          //  obj[i++] = ELEMENT;
          //  ELEMENT = obj;


            return null;
        }

        @Override
        public void remove() {
            int nom = obj.length-index-1;


            System.arraycopy(obj, index+1, obj, index, nom);
            index--;
        }
    }
}

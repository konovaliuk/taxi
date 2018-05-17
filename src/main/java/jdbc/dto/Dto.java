package jdbc.dto;

public abstract class Dto {

    protected final static int SEED = 37;

    public Dto(){
    }

    public abstract Object clone();

    public abstract boolean equals(Object o);

    public abstract int hashCode();

    protected boolean compare(Object a, Object b) {
        return (a==null) ? b==null : a.equals(b);
    }

    protected int hash( int seed , Object o ) {
        if ( o == null) return SEED * seed;
        return SEED * seed * o.hashCode();
    }
}

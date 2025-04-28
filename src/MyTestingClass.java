public class MyTestingClass  {
    private int id1;
    private int id2;

    public MyTestingClass(int id1, int id2) {
        this.id1 = id1;
        this.id2 = id2;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + id1;
        result = 31 * result + id2;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MyTestingClass other = (MyTestingClass) obj;
        return id1 == other.id1 && id2 == other.id2;
    }
}

public class Student {
    private String name;
    private int mark;
    private boolean inSeminar;
    private boolean wasAsked;

    public Student(String name) {
        this.name = name;
    }

    public void setMark(int mark) throws Exception {
        if(mark >= 0 && mark <= 10)
            this.mark = mark;
        else
            throw new Exception("Incorrect mark!");
    }

    public void setInSeminar(boolean inSeminar) {
        wasAsked = true;
        this.inSeminar = inSeminar;
    }

    public String getName() {
        return name;
    }

    public boolean getWasAsked() {
        return wasAsked;
    }

    public int getMark() {
        return mark;
    }

    @Override
    public String toString() {
        return name + ' ' + (inSeminar ? mark : "not in seminar");
    }
}

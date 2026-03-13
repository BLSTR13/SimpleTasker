public class UrgentTask extends Task {
    private int urgency;

    public UrgentTask(String description, int urgency) {
        super(description);
        this.urgency = urgency;
    }

    public int getUrgency() {
        return urgency;
    }

    public void setUrgency(int urgency) {
        this.urgency = urgency;
    }

    @Override
    public String toString() {
        return super.toString() + " | Urgency: " + urgency;
    }
}
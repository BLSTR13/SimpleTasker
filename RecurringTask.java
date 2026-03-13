public class RecurringTask extends Task {
    private String frequency;

    public RecurringTask(String description, String frequency) {
        super(description);
        this.frequency = frequency;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        return super.toString() + " | Frequency: " + frequency;
    }
}
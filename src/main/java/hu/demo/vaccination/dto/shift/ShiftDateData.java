package hu.demo.vaccination.dto.shift;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class ShiftDateData {
    private Timestamp start;
    private Timestamp end;

    public Timestamp getStart() {
        return start;
    }

    public void setStart(Timestamp start) {
        this.start = start;
    }

    public Timestamp getEnd() {
        return end;
    }

    public void setEnd(Timestamp end) {
        this.end = end;
    }
}

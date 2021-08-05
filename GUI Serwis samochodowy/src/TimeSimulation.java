import Service.Service;

import java.time.LocalDate;

public class TimeSimulation extends Thread {
    boolean checkRentStatus = false;
    public static LocalDate simulatedTime = LocalDate.now();
    protected Service service;

    @Override
    public void run() {
        while (true) {
            try {
                TimeSimulation.sleep(5000);
                simulatedTime = simulatedTime.plusDays(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!checkRentStatus) {
                checkRentStatus = true;
            } else {
                service.simTime(simulatedTime);
                checkRentStatus = false;
            }
        }
    }

    public void passService(Service passed) {
        service = passed;
    }
}

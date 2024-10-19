package com.example.labapi2;

public class TraineeRepository {
    public static TraineeServices getTraineeServices() {
        return APIClient.getClient().create(TraineeServices.class);
    }
}

package fr.math.minecraft.server.api.mapper;

public abstract class ApiMapper<T> {

    public abstract T parseData(String responseData);

}

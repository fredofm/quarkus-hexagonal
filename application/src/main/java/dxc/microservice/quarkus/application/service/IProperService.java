package dxc.microservice.quarkus.application.service;

import java.util.List;

import dxc.microservice.quarkus.domain.model.Prop;

public interface IProperService {

    public Prop getProp(Long id);

    public List<Prop> getAllProps();
    
    public Prop activateProp(Long id);

    public void save(Prop prop);

    public void update(Prop prop);
}

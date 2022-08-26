package dxc.microservice.quarkus.application.service;

import java.util.List;
import java.util.Objects;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import dxc.microservice.quarkus.application.repository.IPropRepository;
import dxc.microservice.quarkus.application.service.exceptions.ServiceException;
import dxc.microservice.quarkus.domain.model.Prop;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@AllArgsConstructor
@Slf4j
public class PropService implements IProperService {

    IPropRepository propRepository;

    public Prop getProp(Long id) {      
        log.debug("Getting prop {}", id);
        return propRepository.findById(id);
    }

    @Override
    @Transactional
    public Prop activateProp(Long id) {
        log.debug("Activate prop {}", id);
        Prop prop = getProp(id);

        if (null != prop) {
            prop.setRunning(true);

            propRepository.update(prop);

            log.info("Prop {} was activated.", id);
        } else {
            log.warn("Trying to activate a prop {} that doesn't exists", id);
        }        

        return prop;
    }

    @Override
    @Transactional
    public List<Prop> getAllProps() {
        log.debug("Getting all props");
        return propRepository.getAll();
    }

    @Override
    @Transactional
    public void save(Prop prop) {
        log.debug("Saving prop: {}", prop);

        propRepository.save(prop);
    }

    @Override
    @Transactional
    public void update(Prop prop) {
        log.debug("Updating prop: {}", prop);

        if (Objects.isNull(prop.getId())) {
            throw new ServiceException("Customer does not have a customerId");
        }

        propRepository.update(prop);
    }
}

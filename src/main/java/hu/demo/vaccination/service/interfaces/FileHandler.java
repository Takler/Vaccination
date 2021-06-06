package hu.demo.vaccination.service.interfaces;

import hu.demo.vaccination.dto.InputCreateData;

public interface FileHandler {

    boolean fileSave(InputCreateData input);

    boolean fileLoad(InputCreateData input);

}

package com.dialltay.leveragess.name;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


public interface NameService {
    String getName();
    List<String> getSecretName(String name);
}


@Service
class NameServiceImpl implements NameService {

    private final Map<String, List<String>> secretNames = Map.of(
            "alphamar", List.of("diallo", "atayi"),
            "betamar", List.of("betadiallo", "betayi")
    );

    /**
     * This method is secured with a pre-authorization check.
     * Spring aspects will intercept the call to this method and check if the user has the required authority.
     * @return the name of the user
     */
    @PreAuthorize("hasAuthority('write')")
    @Override
    public String getName() {
        return "Alphamar";
    }

    /**
     * #name refers to the method parameter name.
     * @return the secret names of the user
     */
    @PreAuthorize("#name == authentication.principal.username")
    @Override
    public List<String> getSecretName(String name) {
        return this.secretNames.get(name);
    }
}


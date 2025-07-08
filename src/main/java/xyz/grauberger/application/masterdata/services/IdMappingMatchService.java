package xyz.grauberger.application.masterdata.services;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import xyz.grauberger.application.fdi.DataProvider;
import xyz.grauberger.application.masterdata.repositories.MatchMappingEntityRepository;

@Service
@RequiredArgsConstructor
public class IdMappingMatchService {
    final MatchMappingEntityRepository matchMappingEntityRepository;

    public String getProviderId(long id, DataProvider dataProvider) {
        return matchMappingEntityRepository.findByIdAndProvider(id, dataProvider).getProviderId();
    }

    public long getId(String providerId, DataProvider dataProvider) {
        return matchMappingEntityRepository.findIdByProviderIdAndProvider(providerId, dataProvider).getId().match().getId();
    }
}

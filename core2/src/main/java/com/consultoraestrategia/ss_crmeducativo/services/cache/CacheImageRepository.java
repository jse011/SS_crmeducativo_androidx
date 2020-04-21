package com.consultoraestrategia.ss_crmeducativo.services.cache;

import java.util.List;

public interface CacheImageRepository {
    public void save(List<String> imageUrl, int size);
}

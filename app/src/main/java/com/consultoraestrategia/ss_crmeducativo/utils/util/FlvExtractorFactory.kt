package com.consultoraestrategia.ss_portalalumno.exoPlayer.util

import com.google.android.exoplayer2.extractor.Extractor
import com.google.android.exoplayer2.extractor.ExtractorsFactory
import com.google.android.exoplayer2.extractor.flv.FlvExtractor

public class FlvExtractorFactory : ExtractorsFactory {

    override fun createExtractors(): Array<Extractor> {
        return arrayOf(
            FlvExtractor()
        )
    }
}
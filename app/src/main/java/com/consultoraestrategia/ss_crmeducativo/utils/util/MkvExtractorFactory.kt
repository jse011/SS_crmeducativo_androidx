package com.consultoraestrategia.ss_portalalumno.exoPlayer.util

import com.google.android.exoplayer2.extractor.Extractor
import com.google.android.exoplayer2.extractor.ExtractorsFactory
import com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor

public class MkvExtractorFactory : ExtractorsFactory {

    override fun createExtractors(): Array<Extractor> {
        return arrayOf(
            MatroskaExtractor()
        )
    }
}
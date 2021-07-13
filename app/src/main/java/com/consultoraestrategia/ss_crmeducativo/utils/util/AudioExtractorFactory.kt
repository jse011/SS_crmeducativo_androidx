package com.consultoraestrategia.ss_portalalumno.exoPlayer.util

import com.google.android.exoplayer2.extractor.Extractor
import com.google.android.exoplayer2.extractor.ExtractorsFactory
import com.google.android.exoplayer2.extractor.mp3.Mp3Extractor
import com.google.android.exoplayer2.extractor.ogg.OggExtractor
import com.google.android.exoplayer2.extractor.wav.WavExtractor

public class AudioExtractorFactory : ExtractorsFactory {

    override fun createExtractors(): Array<Extractor> {
        return arrayOf(
            Mp3Extractor(),
            OggExtractor(),
            WavExtractor()
        )
    }
}
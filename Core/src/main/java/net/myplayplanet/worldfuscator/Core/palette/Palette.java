package net.myplayplanet.worldfuscator.Core.palette;

import java.nio.ByteBuffer;
import java.util.Collection;

public interface Palette {
    /**
     * Initializes the correct Palette type based upon bitsPerblock. <br/>
     * The palette may be read and advanced to a position after the palette
     * @param buffer chunk data packet, positioned after full chunk boolean
     * @return
     */
    static Palette getInstance(byte bitsPerBlock, ByteBuffer buffer) {
        if (bitsPerBlock < 9) {
            return new IndirectPalette(buffer);
        } else {
            return new DirectPalette();
        }
    }

    /**
     * Checks if this palette contains any of the specified global palette block state identifier
     * @param globalPaletteIds global palette ids to search for
     * @return true if any of the global palette ids found in palette, otherwise false
     */
    boolean containsAny(Collection<Integer> globalPaletteIds);

    /**
     * Checks if this palette contains the specified global palette block state identifier
     * @param globalPaletteId
     * @return true if this palette contains the specified global palette block state identifier, otherwise false
     */
    boolean contains(Integer globalPaletteId);

    /**
     * Searches for any palette index whose global palette id does not exists in the given list of ids
     * @param globalPaletteIds
     * @return
     */
    Integer searchAnyNonMatching(Collection<Integer> globalPaletteIds);

    /**
     * Translates global palette ids to its corresponding palette index. The palette index is always used in the chunk packet.
     * @param globalPaletteIds global palette ids to translate
     * @return Collection of palette indexes
     */
    Collection<Integer> translate(Collection<Integer> globalPaletteIds);

    /**
     * Translate a global palette id to the corresponding pallte index. The palette index is always used in the chunk packet.
     * @param globalPaletteId
     * @return palette index
     */
    Integer translate(Integer globalPaletteId);
}
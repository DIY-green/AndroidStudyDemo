package com.cheng.utils.security;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;
import java.util.zip.ZipError;

/**
 * APK伪加密
 */
public class PseudoEncryptionUtil {
    public static void main(String[] args) {
        ApkUtilTool apk = new ApkUtilTool();
        try {
            /**
             * 进行伪加密
             */
            apk.ChangToEncryptedEntry("需要伪加密的apk地址", "伪加密后的apk地址");
            /**
             * 进行解密
             */
            apk.FixEncryptedEntry("进行过伪加密的apk地址", "解密后的apk地址");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * Compression methods
     */
    static final int METHOD_STORED = 0;
    static final int METHOD_DEFLATED = 8;
    static final int METHOD_DEFLATED64 = 9;
    static final int METHOD_BZIP2 = 12;
    static final int METHOD_LZMA = 14;
    static final int METHOD_LZ77 = 19;
    static final int METHOD_AES = 99;

    /*
     * General purpose big flag
     */
    static final int FLAG_ENCRYPTED = 0x01;
    static final int FLAG_DATADESCR = 0x08; // crc, size and csize in dd
    static final int FLAG_EFS = 0x800; // If this bit is set the filename and
    // comment fields for this file must be
    // encoded using UTF-8.
    /*
	 * Header signatures
	 */
    static long LOCSIG = 0x04034b50L; // "PK\003\004"
    static long EXTSIG = 0x08074b50L; // "PK\007\008"
    static long CENSIG = 0x02014b50L; // "PK\001\002"
    static long ENDSIG = 0x06054b50L; // "PK\005\006"

    /*
     * Header sizes in bytes (including signatures)
     */
    static final int LOCHDR = 30; // LOC header size
    static final int EXTHDR = 16; // EXT header size
    static final int CENHDR = 46; // CEN header size
    static final int ENDHDR = 22; // END header size

    /*
     * Local file (LOC) header field offsets
     */
    static final int LOCVER = 4; // version needed to extract
    static final int LOCABC = 6; // general purpose bit flag
    static final int LOCHOW = 8; // compression method
    static final int LOCTIM = 10; // modification time
    static final int LOCCRC = 14; // uncompressed file crc-32 value
    static final int LOCSIZ = 18; // compressed size
    static final int LOCLEN = 22; // uncompressed size
    static final int LOCNAM = 26; // filename length
    static final int LOCEXT = 28; // extra field length

    /*
     * Extra local (EXT) header field offsets
     */
    static final int EXTCRC = 4; // uncompressed file crc-32 value
    static final int EXTSIZ = 8; // compressed size
    static final int EXTLEN = 12; // uncompressed size

    /*
     * Central directory (CEN) header field offsets
     */
    static final int CENVEM = 4; // version made by
    static final int CENVER = 6; // version needed to extract
    static final int CENABC = 8; // encrypt, decrypt flags
    static final int CENHOW = 10; // compression method
    static final int CENTIM = 12; // modification time
    static final int CENCRC = 16; // uncompressed file crc-32 value
    static final int CENSIZ = 20; // compressed size
    static final int CENLEN = 24; // uncompressed size
    static final int CENNAM = 28; // filename length
    static final int CENEXT = 30; // extra field length
    static final int CENCOM = 32; // comment length
    static final int CENDSK = 34; // disk number start
    static final int CENATT = 36; // internal file attributes
    static final int CENATX = 38; // external file attributes
    static final int CENOFF = 42; // LOC header offset

    /*
     * End of central directory (END) header field offsets
     */
    static final int ENDSUB = 8; // number of entries on this disk
    static final int ENDTOT = 10; // total number of entries
    static final int ENDSIZ = 12; // central directory size in bytes
    static final int ENDOFF = 16; // offset of first CEN header
    static final int ENDCOM = 20; // zip file comment length

    /*
     * ZIP64 constants
     */
    static final long ZIP64_ENDSIG = 0x06064b50L; // "PK\006\006"
    static final long ZIP64_LOCSIG = 0x07064b50L; // "PK\006\007"
    static final int ZIP64_ENDHDR = 56; // ZIP64 end header size
    static final int ZIP64_LOCHDR = 20; // ZIP64 end loc header size
    static final int ZIP64_EXTHDR = 24; // EXT header size
    static final int ZIP64_EXTID = 0x0001; // Extra field Zip64 header ID

    static final int ZIP64_MINVAL32 = 0xFFFF;
    static final long ZIP64_MINVAL = 0xFFFFFFFFL;

    /*
     * Zip64 End of central directory (END) header field offsets
     */
    static final int ZIP64_ENDLEN = 4; // size of zip64 end of central dir
    static final int ZIP64_ENDVEM = 12; // version made by
    static final int ZIP64_ENDVER = 14; // version needed to extract
    static final int ZIP64_ENDNMD = 16; // number of this disk
    static final int ZIP64_ENDDSK = 20; // disk number of start
    static final int ZIP64_ENDTOD = 24; // total number of entries on this disk
    static final int ZIP64_ENDTOT = 32; // total number of entries
    static final int ZIP64_ENDSIZ = 40; // central directory size in bytes
    static final int ZIP64_ENDOFF = 48; // offset of first CEN header
    static final int ZIP64_ENDEXT = 56; // zip64 extensible data sector

    /*
     * Zip64 End of central directory locator field offsets
     */
    static final int ZIP64_LOCDSK = 4; // disk number start
    static final int ZIP64_LOCOFF = 8; // offset of zip64 end
    static final int ZIP64_LOCTOT = 16; // total number of disks

    /*
     * Zip64 Extra local (EXT) header field offsets
     */
    static final int ZIP64_EXTCRC = 4; // uncompressed file crc-32 value
    static final int ZIP64_EXTSIZ = 8; // compressed size, 8-byte
    static final int ZIP64_EXTLEN = 16; // uncompressed size, 8-byte

    /*
     * Extra field header ID
     */
    static final int EXTID_ZIP64 = 0x0001; // ZIP64
    static final int EXTID_NTFS = 0x000a; // NTFS
    static final int EXTID_UNIX = 0x000d; // UNIX
    static final int EXTID_EFS = 0x0017; // Strong Encryption
    static final int EXTID_EXTT = 0x5455; // Info-ZIP Extended Timestamp

    /*
     * fields access methods
     */
    // /////////////////////////////////////////////////////
    static final int CH(byte[] b, int n) {
        return b[n] & 0xff;
    }

    static final int SH(byte[] b, int n) {
        return (b[n] & 0xff) | ((b[n + 1] & 0xff) << 8);
    }

    static final long LG(byte[] b, int n) {
        return ((SH(b, n)) | (SH(b, n + 2) << 16)) & 0xffffffffL;
    }

    static final long LL(byte[] b, int n) {
        return (LG(b, n)) | (LG(b, n + 4) << 32);
    }

    static final long GETSIG(byte[] b) {
        return LG(b, 0);
    }

    // local file (LOC) header fields
    static final long LOCSIG(byte[] b) {
        return LG(b, 0);
    } // signature

    static final int LOCVER(byte[] b) {
        return SH(b, 4);
    } // version needed to extract

    static final int LOCABC(byte[] b) {
        return SH(b, 6);
    } // general purpose bit flags

    static final int LOCHOW(byte[] b) {
        return SH(b, 8);
    } // compression method

    static final long LOCTIM(byte[] b) {
        return LG(b, 10);
    } // modification time

    static final long LOCCRC(byte[] b) {
        return LG(b, 14);
    } // crc of uncompressed data

    static final long LOCSIZ(byte[] b) {
        return LG(b, 18);
    } // compressed data size

    static final long LOCLEN(byte[] b) {
        return LG(b, 22);
    } // uncompressed data size

    static final int LOCNAM(byte[] b) {
        return SH(b, 26);
    } // filename length

    static final int LOCEXT(byte[] b) {
        return SH(b, 28);
    } // extra field length

    // extra local (EXT) header fields
    static final long EXTCRC(byte[] b) {
        return LG(b, 4);
    } // crc of uncompressed data

    static final long EXTSIZ(byte[] b) {
        return LG(b, 8);
    } // compressed size

    static final long EXTLEN(byte[] b) {
        return LG(b, 12);
    } // uncompressed size

    // end of central directory header (END) fields
    static final int ENDSUB(byte[] b) {
        return SH(b, 8);
    } // number of entries on this disk

    static final int ENDTOT(byte[] b) {
        return SH(b, 10);
    } // total number of entries

    static final long ENDSIZ(byte[] b) {
        return LG(b, 12);
    } // central directory size

    static final long ENDOFF(byte[] b) {
        return LG(b, 16);
    } // central directory offset

    static final int ENDCOM(byte[] b) {
        return SH(b, 20);
    } // size of zip file comment

    static final int ENDCOM(byte[] b, int off) {
        return SH(b, off + 20);
    }

    // zip64 end of central directory recoder fields
    static final long ZIP64_ENDTOD(byte[] b) {
        return LL(b, 24);
    } // total number of entries on disk

    static final long ZIP64_ENDTOT(byte[] b) {
        return LL(b, 32);
    } // total number of entries

    static final long ZIP64_ENDSIZ(byte[] b) {
        return LL(b, 40);
    } // central directory size

    static final long ZIP64_ENDOFF(byte[] b) {
        return LL(b, 48);
    } // central directory offset

    static final long ZIP64_LOCOFF(byte[] b) {
        return LL(b, 8);
    } // zip64 end offset

    // central directory header (CEN) fields
    static final long CENSIG(byte[] b, int pos) {
        return LG(b, pos + 0);
    }

    static final int CENVEM(byte[] b, int pos) {
        return SH(b, pos + 4);
    }

    static final int CENVER(byte[] b, int pos) {
        return SH(b, pos + 6);
    }

    static final int CENABC(byte[] b, int pos) {
        return SH(b, pos + 8);
    }

    static final int CENHOW(byte[] b, int pos) {
        return SH(b, pos + 10);
    }

    static final long CENTIM(byte[] b, int pos) {
        return LG(b, pos + 12);
    }

    static final long CENCRC(byte[] b, int pos) {
        return LG(b, pos + 16);
    }

    static final long CENSIZ(byte[] b, int pos) {
        return LG(b, pos + 20);
    }

    static final long CENLEN(byte[] b, int pos) {
        return LG(b, pos + 24);
    }

    static final int CENNAM(byte[] b, int pos) {
        return SH(b, pos + 28);
    }

    static final int CENEXT(byte[] b, int pos) {
        return SH(b, pos + 30);
    }

    static final int CENCOM(byte[] b, int pos) {
        return SH(b, pos + 32);
    }

    static final int CENDSK(byte[] b, int pos) {
        return SH(b, pos + 34);
    }

    static final int CENATT(byte[] b, int pos) {
        return SH(b, pos + 36);
    }

    static final long CENATX(byte[] b, int pos) {
        return LG(b, pos + 38);
    }

    static final long CENOFF(byte[] b, int pos) {
        return LG(b, pos + 42);
    }

    /* The END header is followed by a variable length comment of size < 64k. */
    static final long END_MAXLEN = 0xFFFF + ENDHDR;
    static final int READBLOCKSZ = 128;

    public static class ApkUtilTool {

        private FileChannel ch; // channel to the zipfile
        private FileChannel fc;

        /**
         * 修复zip伪加密状态的Entry
         *
         * @param inZip
         * @param fixZip
         * @throws IOException
         */
        public void FixEncryptedEntry(File inZip, File fixZip)
                throws IOException {
            changEntry(inZip, fixZip, true);
        }

        /**
         * 修复zip伪加密状态的Entry
         *
         * @param inZip
         * @param fixZip
         * @throws IOException
         */
        public void FixEncryptedEntry(String inZip, String fixZip)
                throws IOException {
            FixEncryptedEntry(new File(inZip), new File(fixZip));
        }

        /**
         * 修改zip的Entry为伪加密状态
         *
         * @param inZip
         * @param storeZip
         * @throws IOException
         */
        public void ChangToEncryptedEntry(File inZip, File storeZip)
                throws IOException {
            changEntry(inZip, storeZip, false);
        }

        /**
         * 修改zip的Entry为伪加密状态
         *
         * @param inZip
         * @param storeZip
         * @throws IOException
         */
        public void ChangToEncryptedEntry(String inZip, String storeZip)
                throws IOException {
            ChangToEncryptedEntry(new File(inZip), new File(storeZip));
        }

        /**
         * 更改zip的Entry为伪加密状态
         *
         * @param inZip
         * @param storeZip
         * @param fix      ture:修复伪加密 false:更改到伪加密
         * @throws IOException
         */
        private void changEntry(File inZip, File storeZip, boolean fix)
                throws IOException {
            FileInputStream fis = new FileInputStream(inZip);
            FileOutputStream fos = new FileOutputStream(storeZip);

            byte[] buf = new byte[10240];
            int len;
            while ((len = fis.read(buf)) != -1) {
                fos.write(buf, 0, len);
            }

            ch = fis.getChannel();
            fc = fos.getChannel();

            changEntry(fix);

            ch.close();
            fc.close();

            fis.close();
            fos.close();
        }

        // Reads zip file central directory. Returns the file position of first
        // CEN header, otherwise returns -1 if an error occured. If zip->msg !=
        // NULL
        // then the error was a zip format error and zip->msg has the error
        // text.
        // Always pass in -1 for knownTotal; it's used for a recursive call.
        private void changEntry(boolean fix) throws IOException {
            END end = findEND();

            if (end.cenlen > end.endpos)
                zerror("invalid END header (bad central directory size)");
            long cenpos = end.endpos - end.cenlen; // position of CEN table

            // Get position of first local file (LOC) header, taking into
            // account that there may be a stub prefixed to the zip file.
            long locpos = cenpos - end.cenoff;
            if (locpos < 0)
                zerror("invalid END header (bad central directory offset)");

            // read in the CEN and END
            byte[] cen = new byte[(int) (end.cenlen + ENDHDR)];
            if (readFullyAt(cen, 0, cen.length, cenpos) != end.cenlen + ENDHDR) {
                zerror("read CEN tables failed");
            }

            int pos = 0;
            int limit = cen.length - ENDHDR;
            while (pos < limit) {
                if (CENSIG(cen, pos) != CENSIG)
                    zerror("invalid CEN header (bad signature)");
                int method = CENHOW(cen, pos);
                int nlen = CENNAM(cen, pos);
                int elen = CENEXT(cen, pos);
                int clen = CENCOM(cen, pos);

                if (fix) {
                    if ((CENABC(cen, pos) & 1) != 0) {
                        byte[] name = Arrays.copyOfRange(cen, pos + CENHDR, pos
                                + CENHDR + nlen);
                        // System.out.println("Found the encrypted entry : "
                        // + new String(name) + ", fix...");
                        // b[n] & 0xff) | ((b[n + 1] & 0xff) << 8
                        cen[pos + 8] &= 0xFE;
                        // cen[pos+8] ^= CEN***(cen, pos) % 2;
                        // cen[pos+8] ^= cen[pos+8] % 2;
                        // zerror("invalid CEN header (encrypted entry)");
                    }
                } else {
                    if ((CENABC(cen, pos) & 1) == 0) {
                        byte[] name = Arrays.copyOfRange(cen, pos + CENHDR, pos
                                + CENHDR + nlen);
                        // System.out.println("Chang the entry : "
                        // + new String(name) + ", Encrypted...");
                        // b[n] & 0xff) | ((b[n + 1] & 0xff) << 8
                        cen[pos + 8] |= 0x1;
                        // zerror("invalid CEN header (encrypted entry)");
                    }
                }

                if (method != METHOD_STORED && method != METHOD_DEFLATED)
                    zerror("invalid CEN header (unsupported compression method: "
                            + method + ")");
                if (pos + CENHDR + nlen > limit)
                    zerror("invalid CEN header (bad header size)");

                // skip ext and comment
                pos += (CENHDR + nlen + elen + clen);
            }

            writeFullyAt(cen, 0, cen.length, cenpos);

            if (pos + ENDHDR != cen.length) {
                zerror("invalid CEN header (bad header size)");
            }
        }

        // Reads len bytes of data from the specified offset into buf.
        // Returns the total number of bytes read.
        // Each/every byte read from here (except the cen, which is mapped).
        final long readFullyAt(byte[] buf, int off, long len, long pos)
                throws IOException {
            ByteBuffer bb = ByteBuffer.wrap(buf);
            bb.position(off);
            bb.limit((int) (off + len));
            return readFullyAt(bb, pos);
        }

        private final long readFullyAt(ByteBuffer bb, long pos)
                throws IOException {
            synchronized (ch) {
                return ch.position(pos).read(bb);
            }
        }

        final long writeFullyAt(byte[] buf, int off, long len, long pos)
                throws IOException {
            ByteBuffer bb = ByteBuffer.wrap(buf);
            bb.position(off);
            bb.limit((int) (off + len));
            return writeFullyAt(bb, pos);
        }

        private final long writeFullyAt(ByteBuffer bb, long pos)
                throws IOException {
            synchronized (fc) {
                return fc.position(pos).write(bb);
            }
        }

        // Searches for end of central directory (END) header. The contents of
        // the END header will be read and placed in endbuf. Returns the file
        // position of the END header, otherwise returns -1 if the END header
        // was not found or an error occurred.
        private END findEND() throws IOException {
            byte[] buf = new byte[READBLOCKSZ];
            long ziplen = ch.size();
            long minHDR = (ziplen - END_MAXLEN) > 0 ? ziplen - END_MAXLEN : 0;
            long minPos = minHDR - (buf.length - ENDHDR);

            for (long pos = ziplen - buf.length; pos >= minPos; pos -= (buf.length - ENDHDR)) {
                int off = 0;
                if (pos < 0) {
                    // Pretend there are some NUL bytes before start of file
                    off = (int) -pos;
                    Arrays.fill(buf, 0, off, (byte) 0);
                }
                int len = buf.length - off;
                if (readFullyAt(buf, off, len, pos + off) != len)
                    zerror("zip END header not found");

                // Now scan the block backwards for END header signature
                for (int i = buf.length - ENDHDR; i >= 0; i--) {
                    if (buf[i + 0] == (byte) 'P' && buf[i + 1] == (byte) 'K'
                            && buf[i + 2] == (byte) '\005'
                            && buf[i + 3] == (byte) '\006'
                            && (pos + i + ENDHDR + ENDCOM(buf, i) == ziplen)) {
                        // Found END header
                        buf = Arrays.copyOfRange(buf, i, i + ENDHDR);
                        END end = new END();
                        end.endsub = ENDSUB(buf);
                        end.centot = ENDTOT(buf);
                        end.cenlen = ENDSIZ(buf);
                        end.cenoff = ENDOFF(buf);
                        end.comlen = ENDCOM(buf);
                        end.endpos = pos + i;
                        if (end.cenlen == ZIP64_MINVAL
                                || end.cenoff == ZIP64_MINVAL
                                || end.centot == ZIP64_MINVAL32) {
                            // need to find the zip64 end;
                            byte[] loc64 = new byte[ZIP64_LOCHDR];
                            if (readFullyAt(loc64, 0, loc64.length, end.endpos
                                    - ZIP64_LOCHDR) != loc64.length) {
                                return end;
                            }
                            long end64pos = ZIP64_LOCOFF(loc64);
                            byte[] end64buf = new byte[ZIP64_ENDHDR];
                            if (readFullyAt(end64buf, 0, end64buf.length,
                                    end64pos) != end64buf.length) {
                                return end;
                            }
                            // end64 found, re-calcualte everything.
                            end.cenlen = ZIP64_ENDSIZ(end64buf);
                            end.cenoff = ZIP64_ENDOFF(end64buf);
                            end.centot = (int) ZIP64_ENDTOT(end64buf); // assume
                            // total
                            // < 2g
                            end.endpos = end64pos;
                        }
                        return end;
                    }
                }
            }
            zerror("zip END header not found");
            return null; // make compiler happy
        }

        static void zerror(String msg) {
            throw new ZipError(msg);
        }

        // End of central directory record
        static class END {
            int disknum;
            int sdisknum;
            int endsub; // endsub
            int centot; // 4 bytes
            long cenlen; // 4 bytes
            long cenoff; // 4 bytes
            int comlen; // comment length
            byte[] comment;

            /* members of Zip64 end of central directory locator */
            int diskNum;
            long endpos;
            int disktot;

            @Override
            public String toString() {
                return "disknum : " + disknum + "\n" + "sdisknum : " + sdisknum
                        + "\n" + "endsub : " + endsub + "\n" + "centot : "
                        + centot + "\n" + "cenlen : " + cenlen + "\n"
                        + "cenoff : " + cenoff + "\n" + "comlen : " + comlen
                        + "\n" + "diskNum : " + diskNum + "\n" + "endpos : "
                        + endpos + "\n" + "disktot : " + disktot;
            }
        }
    }
}

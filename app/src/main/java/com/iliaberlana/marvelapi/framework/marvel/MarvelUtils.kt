package com.iliaberlana.marvelapi.framework.marvel

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

fun String.md5(): String {
        val digest: MessageDigest

        try {
            digest = MessageDigest.getInstance("MD5")
            digest.reset()
            digest.update(this.toByteArray())

            val a = digest.digest()
            val len = a.size
            val sb = StringBuilder(len shl 1)

            for (anA in a) {
                sb.append(Character.forDigit(anA.toInt() and 0xf0 shr 4, 16))
                sb.append(Character.forDigit(anA.toInt() and 0x0f, 16))
            }

            return sb.toString()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }

        return ""
}
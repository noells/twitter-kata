package kata.twitter.frontal

import io.reactivex.rxjava3.core.Observable

interface Console {
    fun readLine()
    fun writeLine(line: String)
    fun getLines(): Observable<String>
}
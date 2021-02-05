package acceptance.utils

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.subjects.PublishSubject
import io.reactivex.rxjava3.subjects.ReplaySubject
import kata.twitter.frontal.Console

class FakeConsole : Console {
    private var linesSubject: PublishSubject<String> = PublishSubject.create()
    private var inputLinesSubject: PublishSubject<String> = PublishSubject.create()
    private var outputLinesSubject: ReplaySubject<String> = ReplaySubject.create()

    override fun readLine() {
        this.inputLinesSubject.subscribeBy(
            onNext = { this.linesSubject.onNext(it) }
        )
    }

    override fun writeLine(line: String) {
        this.outputLinesSubject.onNext(line)
    }

    fun fakeReadLine(line: String) {
        if (line == "FINISH") {
            return this.outputLinesSubject.onComplete()
        }
        this.inputLinesSubject.onNext(line)
    }

    fun resetOutputLines() {
        this.outputLinesSubject = ReplaySubject.create()
    }

    override fun getLines(): Observable<String> {
        return this.linesSubject
    }

    fun getOutputLines(): Observable<String> {
        return this.outputLinesSubject
    }
}
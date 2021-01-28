package kata.twitter.frontal

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.*

class ObservableConsole : Console {
    private var scanner: Scanner = Scanner(System.`in`)
    private var linesSubject: PublishSubject<String> = PublishSubject.create()

    override fun readLine() {
        val line = this.nextLine()
        if (line.contains("exit")) {
            this.linesSubject.onComplete()
        } else {
            this.linesSubject.onNext(line)
        }
    }

    override fun writeLine(line: String) {
        println(line)
    }

    override  fun getLines(): Observable<String> {
        return this.linesSubject
    }


    private fun nextLine(): String {
        print("> ")
        return this.scanner.nextLine()
    }
}
package dimaarts.ru.domain.usecase

import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.subscribers.DisposableSubscriber

abstract class UseCase<T, Params> constructor(
    private val executor: Scheduler,
    private val postExecutor: Scheduler
) {
    private var disposables: CompositeDisposable = CompositeDisposable()

    private fun newDisposables() {
        if(disposables.isDisposed) {
            disposables = CompositeDisposable()
        }
    }


    protected fun execute(observer: DisposableSubscriber<T>, params: Params, flowable: (Params)->Flowable<T>) {
        newDisposables()
        val observable = flowable(params)
            .subscribeOn(executor)
            .observeOn(postExecutor)
        addDisposable(observable.subscribeWith(observer))
    }

    protected fun execute(observer: DisposableObserver<T>, params: Params, observable: (Params)->Observable<T>) {
        newDisposables()
        val obs = observable(params)
            .subscribeOn(executor)
            .observeOn(postExecutor)
        addDisposable(obs.subscribeWith(observer))
    }

    fun dispose() {
        if (!disposables.isDisposed) {
            disposables.dispose()
        }
    }

    private fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }
}
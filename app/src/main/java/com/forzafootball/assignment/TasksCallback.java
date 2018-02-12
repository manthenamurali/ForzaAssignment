package com.forzafootball.assignment;

/**
 * Callback for tasks. When a task is completed or failed, notify the presenter through this
 * callback.
 *
 * Created by Manthena Murali on 2/11/2018.
 */
public interface TasksCallback<T> {

    void onComplete(T result);

    void onError(Throwable exception);
}

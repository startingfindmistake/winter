package com.example.winter.di

import com.example.winter.domain.repository.trash.TrashNoteRepo
import com.example.winter.domain.repository.trash.TrashNoteRepoImpl
import com.example.winter.services.DispatcherProvider
import com.example.winter.services.alarm.AlarmScheduler
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DispatcherModule {

    @Binds
    abstract fun bindDispatcherProvider(
        dispatcherProvider: DispatcherProviderImpl,
    ): DispatcherProvider

    @Binds
    abstract fun bindTrashRepo(
        trashNoteRepository: TrashNoteRepoImpl,
    ): TrashNoteRepo

    @Binds
    abstract fun bindAlarm(
        trashNoteRepository: AlarmSchedulerImpl,
    ): AlarmScheduler
}

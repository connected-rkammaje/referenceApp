package com.tw.networking.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import okhttp3.logging.HttpLoggingInterceptor;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class NetworkingModule_ProvideHttpLoggingInterceptorFactory implements Factory<HttpLoggingInterceptor> {
  @Override
  public HttpLoggingInterceptor get() {
    return provideHttpLoggingInterceptor();
  }

  public static NetworkingModule_ProvideHttpLoggingInterceptorFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static HttpLoggingInterceptor provideHttpLoggingInterceptor() {
    return Preconditions.checkNotNullFromProvides(NetworkingModule.INSTANCE.provideHttpLoggingInterceptor());
  }

  private static final class InstanceHolder {
    private static final NetworkingModule_ProvideHttpLoggingInterceptorFactory INSTANCE = new NetworkingModule_ProvideHttpLoggingInterceptorFactory();
  }
}

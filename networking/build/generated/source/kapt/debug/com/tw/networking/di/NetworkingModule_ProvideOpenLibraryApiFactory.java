package com.tw.networking.di;

import com.tw.networking.OpenLibraryApi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import retrofit2.Retrofit;

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
public final class NetworkingModule_ProvideOpenLibraryApiFactory implements Factory<OpenLibraryApi> {
  private final Provider<Retrofit> retrofitProvider;

  public NetworkingModule_ProvideOpenLibraryApiFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public OpenLibraryApi get() {
    return provideOpenLibraryApi(retrofitProvider.get());
  }

  public static NetworkingModule_ProvideOpenLibraryApiFactory create(
      Provider<Retrofit> retrofitProvider) {
    return new NetworkingModule_ProvideOpenLibraryApiFactory(retrofitProvider);
  }

  public static OpenLibraryApi provideOpenLibraryApi(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(NetworkingModule.INSTANCE.provideOpenLibraryApi(retrofit));
  }
}

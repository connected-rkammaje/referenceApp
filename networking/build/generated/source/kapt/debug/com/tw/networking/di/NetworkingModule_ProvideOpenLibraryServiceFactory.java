package com.tw.networking.di;

import com.tw.networking.OpenLibraryApi;
import com.tw.networking.OpenLibraryService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class NetworkingModule_ProvideOpenLibraryServiceFactory implements Factory<OpenLibraryService> {
  private final Provider<OpenLibraryApi> apiProvider;

  public NetworkingModule_ProvideOpenLibraryServiceFactory(Provider<OpenLibraryApi> apiProvider) {
    this.apiProvider = apiProvider;
  }

  @Override
  public OpenLibraryService get() {
    return provideOpenLibraryService(apiProvider.get());
  }

  public static NetworkingModule_ProvideOpenLibraryServiceFactory create(
      Provider<OpenLibraryApi> apiProvider) {
    return new NetworkingModule_ProvideOpenLibraryServiceFactory(apiProvider);
  }

  public static OpenLibraryService provideOpenLibraryService(OpenLibraryApi api) {
    return Preconditions.checkNotNullFromProvides(NetworkingModule.INSTANCE.provideOpenLibraryService(api));
  }
}

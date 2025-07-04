package com.tw.networking;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class OpenLibraryService_Factory implements Factory<OpenLibraryService> {
  private final Provider<OpenLibraryApi> apiProvider;

  public OpenLibraryService_Factory(Provider<OpenLibraryApi> apiProvider) {
    this.apiProvider = apiProvider;
  }

  @Override
  public OpenLibraryService get() {
    return newInstance(apiProvider.get());
  }

  public static OpenLibraryService_Factory create(Provider<OpenLibraryApi> apiProvider) {
    return new OpenLibraryService_Factory(apiProvider);
  }

  public static OpenLibraryService newInstance(OpenLibraryApi api) {
    return new OpenLibraryService(api);
  }
}

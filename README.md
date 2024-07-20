# Тестовое задание для Incetro

Приложения показывает список ресторанов, с возможностью посмотреть более подробную информацию о каждом из них с помошью API предоставленного Incetro

### Сборка приложения
> [!IMPORTANT]
> Нужно добавить API ключ в файл **./local.properties**
> Получить ключ можно в телеграмм боте Incetro HR

```kotlin
API_KEY=api_key_here
```

### Технологический стек:
- Android SDK
- [Android Jetpack](https://developer.android.com/jetpack)
- [KotlinX Coroutines](https://github.com/Kotlin/kotlinx.coroutines)
- [KotlinX Serialization](https://github.com/Kotlin/kotlinx.serialization)
- [Retrofit](https://square.github.io/retrofit/) + [OkHttp](https://square.github.io/okhttp/)
- [Jetpack Compose](https://developer.android.com/develop/ui/compose)
- [Compose Navigation](https://developer.android.com/develop/ui/compose/navigation)
- [Hilt](https://dagger.dev/hilt/)
- [Coil](https://coil-kt.github.io/coil/)
- [MD3 Compose](https://developer.android.com/develop/ui/compose/designsystems/material3)

### Основные модули
- api - модуль для работы с сетевыми запросами
- data - модуль для работы с данными
- features:* - все фичи приложения
- app - сборка приложения

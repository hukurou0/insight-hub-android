# AuthApi

All URIs are relative to *https://mny9hjtbri.ap-northeast-1.awsapprunner.com*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**getSession**](AuthApi.md#getSession) | **GET** /api/auth/session | セッション確認 |
| [**signIn**](AuthApi.md#signIn) | **POST** /api/auth/signin | ログイン |
| [**signOut**](AuthApi.md#signOut) | **POST** /api/auth/signout | ログアウト |
| [**signUp**](AuthApi.md#signUp) | **POST** /api/auth/signup | アカウント作成 |


<a id="getSession"></a>
# **getSession**
> User getSession()

セッション確認

現在のセッション情報を取得します

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = AuthApi()
try {
    val result : User = apiInstance.getSession()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling AuthApi#getSession")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AuthApi#getSession")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**User**](User.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="signIn"></a>
# **signIn**
> User signIn(authCredentials)

ログイン

メールアドレスとパスワードでログインします

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = AuthApi()
val authCredentials : AuthCredentials =  // AuthCredentials | 
try {
    val result : User = apiInstance.signIn(authCredentials)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling AuthApi#signIn")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AuthApi#signIn")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **authCredentials** | [**AuthCredentials**](AuthCredentials.md)|  | |

### Return type

[**User**](User.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a id="signOut"></a>
# **signOut**
> SignOut200Response signOut()

ログアウト

現在のセッションをログアウトします

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = AuthApi()
try {
    val result : SignOut200Response = apiInstance.signOut()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling AuthApi#signOut")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AuthApi#signOut")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**SignOut200Response**](SignOut200Response.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="signUp"></a>
# **signUp**
> SignUp200Response signUp(authCredentials)

アカウント作成

新規アカウントを作成します

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = AuthApi()
val authCredentials : AuthCredentials =  // AuthCredentials | 
try {
    val result : SignUp200Response = apiInstance.signUp(authCredentials)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling AuthApi#signUp")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AuthApi#signUp")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **authCredentials** | [**AuthCredentials**](AuthCredentials.md)|  | |

### Return type

[**SignUp200Response**](SignUp200Response.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json


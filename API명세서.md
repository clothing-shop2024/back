<h1 style='background-color: rgba(55, 55, 55, 0.4); text-align: center'>API 명세서 </h1>

해당 API 명세서는 '렌트카 서비스'의 REST API를 명세하고 있습니다.

- Domain : <http://localhost:4000> 

***

<h2 style='background-color: rgba(55, 55, 55, 0.2); text-align: center'> Auth 모듈 </h2>  
  
- url : /api/rentcar/auth

***

#### - 로그인  
  
##### 설명

클라이언트로부터 사용자 아이디와 평문의 비밀번호를 입력받고 아이디와 비밀번호가 일치한다면 성공처리가되며 access_token과 해당 토큰의 만료 기간을 반환합니다. 만약 아이디 혹은 비밀번호가 하나라도 틀리다면 실패 처리됩니다. 서버 에러, 데이터베이스 에러, 토큰 생성 에러가 발생할 수 있습니다.

- method : **POST**  
- URL : **/sign-in**  

##### Request

###### Request Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| userId | String | 사용자의 아이디 | O |
| userPassword | String | 사용자의 비밀번호 | O |

###### Example

```bash
curl -v -X POST "http://localhost:4000/api/rentcar/auth/sign-in" \
 -d "userId=service123" \
 -d "userPassword=P!ssw0rd"
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| contentType | 반환하는 Response Body의 Content Type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 메세지 | O |
| accessToken | String | 사용자 토큰값 | O |
| expires | String | 만료기간 | O | 

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
contentType: application/json;charset=UTF-8
{
  "code": "SU",
  "message": "Success.",
  "accessToken": "${ACCESS_TOKEN}"
}
```

**응답 : 실패 (데이터 유효성 검사 실패)**
```bash
HTTP/1.1 400 Bad Request
contentType: application/json;charset=UTF-8
{
  "code": "VF",
  "message": "Validation Failed."
}
```

**응답 : 실패 (로그인 정보 불일치)**
```bash
HTTP/1.1 401 Unauthorized
contentType: application/json;charset=UTF-8
{
  "code": "SF",
  "message": "Sign in Failed."
}
```

**응답 : 실패 (토큰 생성 실패)**
```bash
HTTP/1.1 500 Internal Server Error
contentType: application/json;charset=UTF-8
{
  "code": "TF",
  "message": "Token creation Failed."
}
```

**응답 : 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
contentType: application/json;charset=UTF-8
{
  "code": "DBE",
  "message": "Database Error."
}
```

***

#### - 아이디 중복 확인  
  
##### 설명

클라이언트로부터 아이디를 입력받아 해당하는 아이디가 이미 사용중인 아이디인지 확인합니다. 중복되지 않은 아이디이면 성공처리를 합니다. 만약 중복되는 아이디라면 실패처리를 합니다. 데이터베이스 에러가 발생할 수 있습니다.

- method : **POST**  
- URL : **/id-check**  

##### Request

###### Request Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| userId | String | 중복 확인 할 사용자의 아이디 | O |

###### Example

```bash
curl -v -X POST "http://localhost:4000/api/rentcar/auth/id-check" \
 -d "userId=service123"
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| contentType | 반환하는 Response Body의 Content Type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 메세지 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
contentType: application/json;charset=UTF-8
{
  "code": "SU",
  "message": "Success."
}
```

**응답 : 실패 (데이터 유효성 검사 실패)**
```bash
HTTP/1.1 400 Bad Request
contentType: application/json;charset=UTF-8
{
  "code": "VF",
  "message": "Validation Failed."
}
```

**응답 : 실패 (중복된 아이디)**
```bash
HTTP/1.1 400 Bad Request
contentType: application/json;charset=UTF-8
{
  "code": "DI",
  "message": "Duplicated Id."
}
```

**응답 : 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
contentType: application/json;charset=UTF-8
{
  "code": "DBE",
  "message": "Database Error."
}
```

***

#### - 이메일 인증  
  
##### 설명

클라이언트로부터 이메일을 입력받아 해당하는 이메일이 이미 사용중인 이메일인지 확인하고 사용하고 있지 않은 이메일이라면 4자리의 인증코드를 해당 이메일로 전송합니다. 이메일 전송이 성공적으로 종료되었으면 성공처리를 합니다. 만약 중복된 이메일이거나 이메일 전송에 실패했으면 실패처리를 합니다. 데이터베이스 에러가 발생할 수 있습니다.

- method : **POST**  
- URL : **/email-auth**  

##### Request

###### Header

| name | description | required |
|---|:---:|:---:|

###### Request Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| userEmail | String | 인증 번호를 전송할 사용자 이메일</br>(이메일 형태의 데이터) | O |

###### Example

```bash
curl -v -X POST "http://localhost:4000/api/rentcar/auth/email-auth" \
 -d "userEmail=email@email.com"
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| contentType | 반환하는 Response Body의 Content Type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 메세지 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
contentType: application/json;charset=UTF-8
{
  "code": "SU",
  "message": "Success."
}
```

**응답 : 실패 (데이터 유효성 검사 실패)**
```bash
HTTP/1.1 400 Bad Request
contentType: application/json;charset=UTF-8
{
  "code": "VF",
  "message": "Validation Failed."
}
```

**응답 : 실패 (중복된 이메일)**
```bash
HTTP/1.1 400 Bad Request
contentType: application/json;charset=UTF-8
{
  "code": "DE",
  "message": "Duplicated Email."
}
```

**응답 : 실패 (이메일 전송 실패)**
```bash
HTTP/1.1 500 Internal Server Error
contentType: application/json;charset=UTF-8
{
  "code": "MF",
  "message": "Mail send Failed."
}
```

**응답 : 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
contentType: application/json;charset=UTF-8
{
  "code": "DBE",
  "message": "Database Error."
}
```

***

#### - 이메일 인증 확인
  
##### 설명

클라이언트로부터 이메일과 인증 번호를 입력받아 해당하는 이메일에 전송한 인증번호와 일치하는지 확인합니다. 일치한다면 성공처리를 합니다. 만약 일치하지 않는다면 실패처리를 합니다. 데이터베이스 에러가 발생할 수 있습니다.

- method : **POST**  
- URL : **/email-auth-check**  

##### Request

###### Request Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| userEmail | String | 인증 번호를 확인할 사용자 이메일 | O |
| authNumber | String | 인증 확인할 인증 번호 | O |

###### Example

```bash
curl -v -X POST "http://localhost:4000/api/rentcar/auth/email-auth-check" \
 -d "userEmail=email@email.com" \
 -d "authNumber=0123"
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| contentType | 반환하는 Response Body의 Content Type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 메세지 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
contentType: application/json;charset=UTF-8
{
  "code": "SU",
  "message": "Success."
}
```

**응답 : 실패 (데이터 유효성 검사 실패)**
```bash
HTTP/1.1 400 Bad Request
contentType: application/json;charset=UTF-8
{
  "code": "VF",
  "message": "Validation Failed."
}
```

**응답 : 실패 (이메일 인증 실패)**
```bash
HTTP/1.1 401 Unauthorized
contentType: application/json;charset=UTF-8
{
  "code": "AF",
  "message": "Authentication Failed."
}
```

**응답 : 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
contentType: application/json;charset=UTF-8
{
  "code": "DBE",
  "message": "Database Error."
}
```

***

#### - 회원가입
  
##### 설명

클라이언트로부터 아이디, 비밀번호, 이메일, 인증번호 입력받아 회원가입 처리를 합니다. 정상적으로 회원가입이 완료되면 성공처리를 합니다. 만약 중복된 아이디, 중복된 이메일, 인증번호 불일치가 발생하면 실패처리를 합니다. 데이터베이스 에러가 발생할 수 있습니다.

- method : **POST**  
- URL : **/sign-up**  

##### Request

###### Request Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| userId | String | 사용자 아이디 | O |
| userPassword | String | 사용자 비밀번호 (영문+숫자 8~13자) | O |
| nickName | String | 사용자 닉네임 | O |
| userEmail | String | 사용자 이메일 (이메일 형태의 데이터) | O |
| authNumber | String | 인증 확인할 인증 번호 | O |

###### Example

```bash
curl -v -X POST "http://localhost:4000/api/rentcar/auth/sign-up" \
 -d "userId=service123" \
 -d "userPassword=Pa55w0rd" \
 -d "nickName=nickName" \
 -d "userEmail=email@email.com" \
 -d "authNumber=0123"
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| contentType | 반환하는 Response Body의 Content Type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 메세지 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
contentType: application/json;charset=UTF-8
{
  "code": "SU",
  "message": "Success."
}
```

**응답 : 실패 (데이터 유효성 검사 실패)**
```bash
HTTP/1.1 400 Bad Request
contentType: application/json;charset=UTF-8
{
  "code": "VF",
  "message": "Validation Failed."
}
```

**응답 : 실패 (중복된 아이디)**
```bash
HTTP/1.1 400 Bad Request
contentType: application/json;charset=UTF-8
{
  "code": "DI",
  "message": "Duplicated Id."
}
```

**응답 : 실패 (중복된 이메일)**
```bash
HTTP/1.1 400 Bad Request
contentType: application/json;charset=UTF-8
{
  "code": "DE",
  "message": "Duplicated Email."
}
```

**응답 : 실패 (이메일 인증 실패)**
```bash
HTTP/1.1 401 Unauthorized
contentType: application/json;charset=UTF-8
{
  "code": "AF",
  "message": "Authentication Failed."
}
```

**응답 : 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
contentType: application/json;charset=UTF-8
{
  "code": "DBE",
  "message": "Database Error."
}
```

<h2 style='background-color: rgba(55, 55, 55, 0.2); text-align: center'> user 모듈 </h2>

- url : /api/rentcar/user

***

#### - 아이디 찾기  
  
##### 설명

클라이언트로부터 이메일을 입력받아 해당하는 이메일이 존재하는 이메일인지 확인하고 존재하는 이메일이면 그에 해당하는 아이디를 반환합니다. 아이디 반환이 성공적으로 반환되면 성공처리를 합니다. 만약 존재하지 않는 이메일일 경우 실패처리를 합니다. 인증 실패, 서버 에러, 데이터베이스 에러가 발생할 수 있습니다.

- method : **POST**  
- URL : **/find-id**  

##### Request

###### Header

| name | description | required |
|---|:---:|:---:|

###### Request Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| userEmail | String | 인증 번호를 전송할 사용자 이메일</br>(이메일 형태의 데이터) | O |

###### Example

```bash
curl -v -X POST "http://localhost:4000/api/rentcar/auth/find-id" \
 -d "userEmail=email@email.com"
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| contentType | 반환하는 Response Body의 Content Type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 메세지 | O |
| userId | String | 사용자의 아이디 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
contentType: application/json;charset=UTF-8
{
  "code": "SU",
  "message": "Success.",
  "userId": "service"
}
```

**응답 : 실패 (데이터 유효성 검사 실패)**
```bash
HTTP/1.1 400 Bad Request
contentType: application/json;charset=UTF-8
{
  "code": "VF",
  "message": "Validation Failed."
}
```

**응답 : 실패 (존재하지 않는 이메일)**
```bash
HTTP/1.1 401 Bad Request
contentType: application/json;charset=UTF-8
{
  "code": "NE",
  "message": "No Exist Email."
}
```

**응답 : 실패 (아이디 찾기 인증 실패)**
```bash
HTTP/1.1 401 Unauthorized
contentType: application/json;charset=UTF-8
{
  "code": "AF",
  "message": "Authentication Failed."
}
```

**응답 : 실패 (인가 실패)**
```bash
HTTP/1.1 403 Forbidden
contentType: application/json;charset=UTF-8
{
  "code": "AF",
  "message": "Authorization Failed."
}
```

**응답 : 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
contentType: application/json;charset=UTF-8
{
  "code": "DBE",
  "message": "Database Error."
}
```

***

#### - 비밀번호 찾기
  
##### 설명

클라이언트로부터 아이디와 이메일을 입력받아 해당하는 아이디와 이메일이 데이터베이스에 존재하는지 확인합니다. 아이디와 이메일이 존재하면 성공처리를 합니다. 만약 아이디 또는 이메일이 존재하지 않으면 실패처리를 합니다. 인증 실패, 서버에러, 데이터베이스 에러가 발생할 수 있습니다.

- method : **POST**  
- URL : **/find-password**

##### Request

###### Request Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| userId | String | 사용자의 아이디 | O |
| userEmail | String | 사용자의 이메일</br>(이메일 형태의 데이터) | O |

###### Example

```bash
curl -v -X POST "http://localhost:4000/api/rentcar/auth/find-password" \
 -d "userId=service123" 
 -d "userEmail=service123@naver.com" 
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| contentType | 반환하는 Response Body의 Content Type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 메세지 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
contentType: application/json;charset=UTF-8
{
  "code": "SU",
  "message": "Success."
}
```

**응답 : 실패 (데이터 유효성 검사 실패)**
```bash
HTTP/1.1 400 Bad Request
contentType: application/json;charset=UTF-8
{
  "code": "VF",
  "message": "Validation Failed."
}
```

**응답 : 실패 (비밀번호 찾기 인증 실패)**
```bash
HTTP/1.1 401 Bad Request
contentType: application/json;charset=UTF-8
{
  "code": "AF",
  "message": "Authorization Failed."
}
```

**응답 : 실패 (존재하지 않는 아이디)**
```bash
HTTP/1.1 401 Bad Request
contentType: application/json;charset=UTF-8
{
  "code": "NUI",
  "message": "No Exist User Id."
}
```

**응답 : 실패 (존재하지 않는 이메일)**
```bash
HTTP/1.1 401 Bad Request
contentType: application/json;charset=UTF-8
{
  "code": "NE",
  "message": "No Exist Email."
}
```

**응답 : 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
contentType: application/json;charset=UTF-8
{
  "code": "DBE",
  "message": "Database Error."
}
```

***
#### - 비밀번호 재설정  
  
##### 설명

클라이언트로부터 비밀번호를 입력받아 기존의 비밀번호를 변경합니다. 변경에 성공하면 성공처리를 합니다. 만약 변경에 실패하면 실패처리를 합니다. 인증 실패, 서버에러, 데이터베이스 에러가 발생할 수 있습니다.

- method : **PUT**  
- URL : **/find-password/{userId}**  

##### Request

###### Request Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| userPassword | String | 사용자의 비밀번호 | O |

###### Example

```bash
curl -v -X PUT "http://localhost:4000/api/rentcar/auth/find-password/${userId}" \
 -d "userPassword={userPassword}" \
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| contentType | 반환하는 Response Body의 Content Type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 메세지 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
contentType: application/json;charset=UTF-8
{
  "code": "SU",
  "message": "Success."
}
```

**응답 : 실패 (데이터 유효성 검사 실패)**
```bash
HTTP/1.1 400 Bad Request
contentType: application/json;charset=UTF-8
{
  "code": "VF",
  "message": "Validation Failed."
}
```

**응답 : 실패 (비밀번호 재설정 인증 실패)**
```bash
HTTP/1.1 401 Unauthorized
contentType: application/json;charset=UTF-8
{
  "code": "AF",
  "message": "Authentication Failed."
}
```

**응답 : 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
contentType: application/json;charset=UTF-8
{
  "code": "DBE",
  "message": "Database Error."
}
```

***

#### - 로그인 유저 정보 반환  
  
##### 설명

클라이언트로부터 Request Header의 Authorization 필드로 Bearer 토큰을 포함하여 요청을 받으면 해당 토큰의 작성자(subject)에 해당하는 사용자 정보를 반환합니다. 성공시에는 사용자의 아이디와 권한을 반환합니다. 인증 실패 및 데이터베이스 에러가 발생할 수 있습니다.

- method : **GET**  
- URL : **/**  

##### Request

###### Header

| name | description | required |
|---|:---:|:---:|
| Authorization | 인증에 사용될 Bearer 토큰 | O |

###### Example

```bash
curl -v -X GET "http://localhost:4000/api/rentcar/user/" \
 -H "Authorization: Bearer {JWT}"
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| contentType | 반환하는 Response Body의 Content Type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 메세지 | O |
| userId | String | 사용자의 아이디 | O |
| userRole | String | 사용자의 권한 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
contentType: application/json;charset=UTF-8
{
  "code": "SU",
  "message": "Success.",
  "userId": "${userId}",
  "userRole": "${userRole}"
}
```

**응답 : 실패 (인증 실패)**
```bash
HTTP/1.1 401 Unauthorized
contentType: application/json;charset=UTF-8
{
  "code": "AF",
  "message": "Authentication Failed."
}
```

**응답 : 실패 (인가 실패)**
```bash
HTTP/1.1 403 Forbidden
contentType: application/json;charset=UTF-8
{
  "code": "AF",
  "message": "Authorization Failed."
}
```

**응답 : 실패 (데이터베이스 에러)**
```bash
HTTP/1.1 500 Internal Server Error
contentType: application/json;charset=UTF-8
{
  "code": "DBE",
  "message": "Database Error."
}
```
***

<h2 style='background-color: rgba(55, 55, 55, 0.2); text-align: center'> notice 모듈 </h2>

- url : /api/shop/notice 

***

#### - 공지사항 전체 게시물 리스트 불러오기

##### 설명

공지사항 페이지에서 작성일 기준 내림차순으로 공지사항 리스트를 반환합니다. 만약 불러오기에 실패하면 실패처리를 합니다. 데이터베이스 에러가 발생할 수 있습니다.

- method : **GET**  
- URL : **/list**  

##### Request

###### Example

```bash
curl -v -X GET "http://localhost:4000/api/shop/notice/list" \
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| contentType | 반환하는 Response Body의 Content Type (application/json) | O |

###### Response Body
| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 메세지 | O |
| noticeList | noticeListItem[] | 공지사항 리스트 | O |

**noticeListItem**
| name | type | description | required |
|---|:---:|:---:|:---:|
| noticeNumber | int | 공지사항 고유번호 | O |
| noticeTitle | String | 제목 | O |
| noticeWriterId | String | 작성자 | O |
| noticeDate | String | 작성일</br>(yyyy.mm.dd 형태) | O |
| viewCount | int | 조회수 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
contentType: application/json;charset=UTF-8
{
  "code": "SU",
  "message": "Success.",
  "noticeList": [
    {
      "noticeNumber": 1,
      "noticeTitle": "제목",
      "noticeWriterId" : "관리자",
      "noticeDate": "2024.08.04",
      "viewCount": 1
    }, ...
  ]
}
```

**응답 : 실패 (데이터베이스 오류)**
```bash
HTTP/1.1 500 Internal Server Error
contentType: application/json;charset=UTF-8
{
  "code": "DBE",
  "message": "Database Error."
}
```

***

#### - 공지사항 상세 게시물 불러오기
  
##### 설명

공지사항 페이지에서 공지사항 고유번호를 입력받고 요청을 보내면 해당하는 공지사항 데이터를 반환합니다. 만약 불러오기에 실패하면 실패처리를 합니다. 데이터베이스 에러가 발생할 수 있습니다.

- method : **GET**  
- URL : **/list/{noticeNumber}**  

##### Request

###### Path Variable

| name | type | description | required |
|---|:---:|:---:|:---:|
| noticeNumber | int | 공지사항 고유번호 | O |

###### Example

```bash
curl -v -X GET "http://localhost:4000/api/shop/notice/list/${noticeNumber}" 
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| contentType | 반환하는 Response Body의 Content Type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 메세지 | O |
| noticeNumber | Integer | 공지사항 고유번호 | O |
| noticeTitle | String | 제목 | O |
| noticeContents | String | 내용 | O |
| noticeWriterId | String | 작성자 | O |
| noticeDate | String | 작성일</br>(yyyy.mm.dd 형태) | O |
| viewCount | int | 조회수 | O |
| noticeImageUrl | String | 이미지 | X |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
contentType: application/json;charset=UTF-8
{
  "code": "SU",
  "message": "Success.",
  "noticeNumber": ${noticeNumber},
  "noticeTitle": "${noticeTitle}",
  "noticeContents": "${noticeContents}",
  "noticeWriterId": "${noticeWriterId}",
  "noticeDate": "${noticeDate}",
  "viewCount": ${viewCount},
  "noticeImageUrl": ${noticeImageUrl}
}
```

**응답 : 실패 (데이터베이스 오류)**
```bash
HTTP/1.1 500 Internal Server Error
contentType: application/json;charset=UTF-8
{
  "code": "DBE",
  "message": "Database Error."
}
```

***

#### - 공지사항 작성하기
  
##### 설명

클라이언트로부터 Request Header의 Authorization 필드로 Bearer 토큰을 포함하여 제목, 내용, 사진을 입력받고 작성에 성공하면 성공처리 합니다. 만약 작성에 실패하면 실패처리를 합니다. 인가 실패, 인증 실패, 데이터베이스 에러가 발생할 수 있습니다.

- method : **POST**  
- URL : **/regist**  

##### Request

###### Header

| name | description | required |
|---|:---:|:---:|
| Authorization | 인증에 사용될 Bearer 토큰 | O |

###### Request Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| noticeTitle | String | 제목 | O |
| noticeContents | String | 내용 | O |
| noticeImageUrl | String | 이미지 | X |

###### Example

```bash
curl -v -X POST "http://localhost:4000/api/shop/notice/regist \
 -H "Authorization: Bearer {JWT}" \
 -d "noticeTitle={noticeTitle}" \
 -d "noticeContents={noticeContents}" \
 -d "noticeImageUrl={noticeImageUrl}"
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| contentType | 반환하는 Response Body의 Content Type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 메세지 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
contentType: application/json;charset=UTF-8
{
  "code": "SU",
  "message": "Success.",
}
```

**응답 : 실패 (인증 실패)**
```bash
HTTP/1.1 401 Unauthorized
contentType: application/json;charset=UTF-8
{
  "code": "AF",
  "message": "Authentication Failed."
}
```

**응답 : 실패 (인가 실패)**
```bash
HTTP/1.1 403 Forbidden
contentType: application/json;charset=UTF-8
{
  "code": "AF",
  "message": "Authorization Failed."
}
```

**응답 : 실패 (데이터베이스 오류)**
```bash
HTTP/1.1 500 Internal Server Error
contentType: application/json;charset=UTF-8
{
  "code": "DBE",
  "message": "Database Error."
}
```

***

#### - 공지사항 수정하기
  
##### 설명

클라이언트로부터 Request Header의 Authorization 필드로 Bearer 토큰을 포함하여 공지사항 고유번호와 제목, 내용, 사진을 입력받고 수정에 성공하면 성공처리 합니다. 만약 수정에 실패하면 실패처리를 합니다. 인가 실패, 인증 실패, 데이터베이스 에러, 데이터 유효성 검사 실패가 발생할 수 있습니다.

- method : **PUT**  
- URL : **/{noticeNumber}/modify**  

##### Request

###### Header

| name | description | required |
|---|:---:|:---:|
| Authorization | 인증에 사용될 Bearer 토큰 | O |

###### Path Variable

| name | type | description | required |
|---|:---:|:---:|:---:|
| noticeNumber | int | 공지사항 고유번호 | O |

###### Request Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| noticeTitle | String | 제목 | O |
| noticeContents | String | 내용 | O |
| noticeImageUrl | String | 이미지 | X |


###### Example

```bash
curl -v -X PUT "http://localhost:4000/api/shop/notice/list/${noticeNumber}/modify" \
 -H "Authorization: Bearer {JWT}" \
 -d "noticeTitle={noticeTitle}" \
 -d "noticeContents={noticeContents}" \
 -d "noticeImageUrl={noticeImageUrl}"
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| contentType | 반환하는 Response Body의 Content Type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 메세지 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
contentType: application/json;charset=UTF-8
{
  "code": "SU",
  "message": "Success.",
}
```

**응답 : 실패 (데이터 유효성 검사 실패)**
```bash
HTTP/1.1 400 Bad Request
contentType: application/json;charset=UTF-8
{
  "code": "VF",
  "message": "Validation Failed."
}
```

**응답 : 실패 (존재하지 않는 게시물)**
```bash
HTTP/1.1 400 Bad Request
contentType: application/json;charset=UTF-8
{
  "code": "NB",
  "message": "No Exist Board."
}
```

**응답 : 실패 (인가 실패)**
```bash
HTTP/1.1 403 Forbidden
contentType: application/json;charset=UTF-8
{
  "code": "AF",
  "message": "Authorization Failed."
}
```

**응답 : 실패 (인증 실패)**
```bash
HTTP/1.1 401 Unauthorized
contentType: application/json;charset=UTF-8
{
  "code": "AF",
  "message": "Authentication Failed."
}
```

**응답 : 실패 (데이터베이스 오류)**
```bash
HTTP/1.1 500 Internal Server Error
contentType: application/json;charset=UTF-8
{
  "code": "DBE",
  "message": "Database Error."
}
```

***

#### - 공지사항 게시물 조회수 증가  
  
##### 설명

 공지사항 고유번호를 입력받아 해당하는 공지사항 게시물의 조회수를 증가합니다. 만약 증가에 실패하면 실패처리를 합니다. 인가 실패, 데이터베이스 에러, 데이터 유효성 검사 실패가 발생할 수 있습니다.

- method : **PATCH**  
- URL : **/{noticeNumber}/increase-view-count**  

##### Request

###### Path Variable

| name | type | description | required |
|---|:---:|:---:|:---:|
| noticeNumber | int | 공지사항 고유번호 | O |

###### Example

```bash
curl -v -X PATCH "http://localhost:4000/api/shop/notice/${noticeNumber}/increase-view-count" \
 -H "Authorization: Bearer {JWT}"
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| contentType | 반환하는 Response Body의 Content Type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 메세지 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
contentType: application/json;charset=UTF-8
{
  "code": "SU",
  "message": "Success."
}
```

**응답 : 실패 (데이터 유효성 검사 실패)**
```bash
HTTP/1.1 400 Bad Request
contentType: application/json;charset=UTF-8
{
  "code": "VF",
  "message": "Validation Failed."
}
```

**응답 : 실패 (존재하지 않는 게시물)**
```bash
HTTP/1.1 400 Bad Request
contentType: application/json;charset=UTF-8
{
  "code": "NB",
  "message": "No Exist Board."
}
```

**응답 : 실패 (데이터베이스 오류)**
```bash
HTTP/1.1 500 Internal Server Error
contentType: application/json;charset=UTF-8
{
  "code": "DBE",
  "message": "Database Error."
}
```

***

#### - 공지사항 게시물 삭제하기
  
##### 설명

클라이언트로부터 Request Header의 Authorization 필드로 Bearer 토큰을 포함하여 공지사항 고유번호를 입력받고 요청을 보내면 해당하는 게시물이 삭제됩니다. 만약 삭제에 실패하면 실패처리를 합니다. 인가 실패, 인증 실패, 데이터베이스 에러가 발생할 수 있습니다.

- method : **DELETE**  
- URL : **/{noticeNumber}/delete**  

##### Request

###### Header

| name | description | required |
|---|:---:|:---:|
| Authorization | 인증에 사용될 Bearer 토큰 | O |

###### Path Variable

| name | type | description | required |
|---|:---:|:---:|:---:|
| noticeNumber | int | 공지사항 등록번호 | O |

###### Example

```bash
curl -v -X DELETE "http://localhost:4000/api/shop/notice/${noticeNumber}/delete" \
 -H "Authorization: Bearer {JWT}"
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| contentType | 반환하는 Response Body의 Content Type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 메세지 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
contentType: application/json;charset=UTF-8
{
  "code": "SU",
  "message": "Success."
}
```

**응답 : 실패 (데이터 유효성 검사 실패)**
```bash
HTTP/1.1 400 Bad Request
contentType: application/json;charset=UTF-8
{
  "code": "VF",
  "message": "Validation Failed."
}
```

**응답 : 실패 (존재하지 않는 게시물)**
```bash
HTTP/1.1 400 Bad Request
contentType: application/json;charset=UTF-8
{
  "code": "NB",
  "message": "No Exist Board."
}
```

**응답 : 실패 (인가 실패)**
```bash
HTTP/1.1 403 Forbidden
contentType: application/json;charset=UTF-8
{
  "code": "AF",
  "message": "Authorization Failed."
}
```

**응답 : 실패 (데이터베이스 오류)**
```bash
HTTP/1.1 500 Internal Server Error
contentType: application/json;charset=UTF-8
{
  "code": "DBE",
  "message": "Database Error."
}
```

***

<h2 style='background-color: rgba(55, 55, 55, 0.2); text-align: center'> qna 모듈 </h2>

- url : /api/shop/qna 

***

#### - 문의사항 전체 리스트 불러오기  

##### 설명

작성일 기준 내림차순으로 문의사항 전체 리스트를 반환합니다. 만약 불러오기에 실패하면 실패처리를 합니다. 데이터베이스 에러가 발생할 수 있습니다.

- method : **GET**  
- URL : **/list**  

##### Request

###### Example

```bash
curl -v -X GET "http://localhost:4000/api/shop/qna/list" \
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| contentType | 반환하는 Response Body의 Content Type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 메세지 | O |
| qnaList | qnaListItem[] | 문의사항 리스트 | O |

**qnaListItem**
| name | type | description | required |
|---|:---:|:---:|:---:|
| qnaNumber | int | 문의사항 고유번호 | O |
| status | boolean | 답변 여부 | O |
| qnaTitle | String | 제목 | O |
| qnaWriterId | String | 작성자 아이디</br>(앞에 세글자를 제외한 나머지 문자는 *) | O |
| qnaDate | String | 작성일</br>(yyyy.mm.dd 형태) | O |
| qnaCategory | String | 유형 | O |
| viewCount | int | 조회수 | O |
| qnaPublic | boolean | 공개 여부 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
contentType: application/json;charset=UTF-8
{
  "code": "SU",
  "message": "Success.",
  "qnaList": [
    {
      "qnaNumber": 1,
      "status": "미답변",
      "qnaTitle": "테스트1",
      "qnaWriterId": "ser****",
      "qnaDate": "2024.05.02",
      "qnaCategory": "상품문의",
      "viewCount": 0,
      "qnaPublic": "공개"
    }, ...
  ]
}
```

**응답 : 실패 (데이터베이스 오류)**
```bash
HTTP/1.1 500 Internal Server Error
contentType: application/json;charset=UTF-8
{
  "code": "DBE",
  "message": "Database Error."
}
```

***

#### - 문의사항 검색 리스트 불러오기  
  
##### 설명

검색어를 입력받고 요청을 보내면 작성일 기준 내림차순으로 검색어에 해당하는 문의사항 리스트를 반환합니다. 만약 불러오기에 실패하면 실패처리를 합니다. 데이터베이스 에러가 발생할 수 있습니다.

- method : **GET**  
- URL : **/list/search**  

##### Request

###### Query Param

| name | type | description | required |
|---|:---:|:---:|:---:|
| searchWord | String | 검색어 | O |

###### Example

```bash
curl -v -X GET "http://localhost:4000/api/shop/qna/list/search?word=${searchWord}" \
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| contentType | 반환하는 Response Body의 Content Type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 메세지 | O |
| qnaList | qnaListItem[] | 문의사항 리스트 | O |

**qnaListItem**
| name | type | description | required |
|---|:---:|:---:|:---:|
| qnaNumber | int | 문의사항 고유번호 | O |
| status | boolean | 답변여부 | O |
| qnaTitle | String | 제목 | O |
| qnaWriterId | String | 작성자 아이디</br>(앞에 세글자를 제외한 나머지 문자는 *) | O |
| qnaDate | String | 작성일</br>(yyyy.mm.dd 형태) | O |
| qnaCategory | String | 유형 | O |
| viewCount | int | 조회수 | O |
| qnaPublic | boolean | 공개 여부 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
contentType: application/json;charset=UTF-8
{
  "code": "SU",
  "message": "Success.",
  "qnaList": [
    {
      "qnaNumber": 1,
      "status": "미답변",
      "qnaTitle": "테스트1",
      "qnaWriterId": "ser****",
      "qnaDate": "2024.05.02",
      "qnaCategory": "상품문의",
      "viewCount": 0,
      "qnaPublic": "공개"
    }, ...
  ]
}
```

**응답 : 실패 (데이터 유효성 검사 실패)**
```bash
HTTP/1.1 400 Bad Request
contentType: application/json;charset=UTF-8
{
  "code": "VF",
  "message": "Validation Failed."
}
```

**응답 : 실패 (데이터베이스 오류)**
```bash
HTTP/1.1 500 Internal Server Error
contentType: application/json;charset=UTF-8
{
  "code": "DBE",
  "message": "Database Error."
}
```

***

#### - 문의사항 마이페이지에서 해당 사용자의 문의 내역 불러오기 

##### 설명

클라이언트로부터 Request Header의 Authorization 필드로 Bearer 토큰을 포함하여 사용자의 아이디를 입력받고 요청을 보내면 작성일 기준 내림차순으로 나의 문의내역을 반환합니다. 만약 불러오기에 실패하면 실패처리를 합니다. 인가 실패, 인증 실패, 데이터베이스 에러, 데이터 유효성 검사 실패가 발생할 수 있습니다.

- method : **GET**
- URL : **/mylist**

##### Request

###### Header

| name | description | required |
|---|:---:|:---:|
| Authorization | 인증에 사용될 Bearer 토큰 | O |

###### Example

```bash
curl -v -X GET "http://localhost:4000/api/shop/qna/mylist" \
 -H "Authorization: Bearer {JWT}"
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| contentType | 반환하는 Response Body의 Content Type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 메세지 | O |
| qnaList | qnaListItem[] | 문의사항 리스트 | O |

**qnaListItem**
| name | type | description | required |
|---|:---:|:---:|:---:|
| qnaNumber | int | 문의사항 고유번호 | O |
| status | boolean | 답변 여부 | O |
| qnaTitle | String | 제목 | O |
| qnaWriterId | String | 작성자 아이디</br>(앞에 세글자를 제외한 나머지 문자는 *) | O |
| qnaDate | String | 작성일</br>(yyyy.mm.dd 형태) | O |
| qnaCategory | String | 유형 | O |
| viewCount | int | 조회수 | O |
| qnaPublic | boolean | 공개 여부 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
contentType: application/json;charset=UTF-8
{
  "code": "SU",
  "message": "Success.",
  "qnaList": [
    {
      "qnaNumber": 1,
      "status": "미답변",
      "qnaTitle": "테스트1",
      "qnaWriterId": "ser****",
      "qnaDate": "2024.05.02",
      "qnaCategory": "상품문의",
      "viewCount": 0,
      "qnaPublic": "공개"
    }, ...
  ]
}
```

**응답 : 실패 (인가 실패)**
```bash
HTTP/1.1 403 Forbidden
contentType: application/json;charset=UTF-8
{
  "code": "AF",
  "message": "Authorization Failed."
}
```

**응답 : 실패 (인증 실패)**
```bash
HTTP/1.1 401 Unauthorized
contentType: application/json;charset=UTF-8
{
  "code": "AF",
  "message": "Authentication Failed."
}
```

**응답 : 실패 (데이터 유효성 검사 실패)**
```bash
HTTP/1.1 400 Bad Request
contentType: application/json;charset=UTF-8
{
  "code": "VF",
  "message": "Validation Failed."
}
```

**응답 : 실패 (데이터베이스 오류)**
```bash
HTTP/1.1 500 Internal Server Error
contentType: application/json;charset=UTF-8
{
  "code": "DBE",
  "message": "Database Error."
}
```

***

#### - 문의사항 상세 내용 불러오기  
  
##### 설명

문의사항 상세 내용 데이터를 반환합니다. 만약 불러오기에 실패하면 실패처리를 합니다. 인가 실패, 데이터베이스 에러, 데이터베이스 유효성 검사 실패가 발생할 수 있습니다.

- method : **GET**  
- URL : **/list/{qnaNumber}**  

##### Request

###### Path Variable

| name | type | description | required |
|---|:---:|:---:|:---:|
| qnaNumber | int | 문의사항 고유번호 | O |

###### Example

```bash
curl -v -X GET "http://localhost:4000/api/shop/qna/list/${qnaNumber}" \
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| contentType | 반환하는 Response Body의 Content Type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 메세지 | O |
| qnaTitle | String | 제목 | O |
| qnaContents | String | 내용 | O |
| qnaWriterId | String | 작성자 아이디</br>(앞에 세글자를 제외한 나머지 문자는 *) | O |
| qnaDate | String | 작성일</br>(yyyy.mm.dd 형태) | O |
| viewCount | int | 조회수 | O |
| qnaCategory | String | 유형 | O |
| qnaPublic | String | 공개여부 | O |
| comment | String | 답글 내용 | X |
| imageUrl | String | 이미지 | X |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
contentType: application/json;charset=UTF-8
{
  "code": "SU",
  "message": "Success.",
  "qnaTitle": "${qnaTitle}",
  "qnaWriterId": "${userId}",
  "qnaDate": "${qnaDate}",
  "viewCount": ${viewCount},
  "qnaCategory": "${qnaCategory}",
  "qnaPublic": "${qnaPublic}",
  "qnaComment": "${qnaComment}",
  "qnaImageUrl": "${qnaImageUrl}"
}
```

**응답 : 실패 (데이터 유효성 검사 실패)**
```bash
HTTP/1.1 400 Bad Request
contentType: application/json;charset=UTF-8
{
  "code": "VF",
  "message": "Validation Failed."
}
```

**응답 : 실패 (존재하지 않는 게시물)**
```bash
HTTP/1.1 400 Bad Request
contentType: application/json;charset=UTF-8
{
  "code": "NB",
  "message": "No Exist Board."
}
```

**응답 : 실패 (데이터베이스 오류)**
```bash
HTTP/1.1 500 Internal Server Error
contentType: application/json;charset=UTF-8
{
  "code": "DBE",
  "message": "Database Error."
}
```

***

#### - 문의사항 작성하기  

##### 설명

클라이언트로부터 Request Header의 Authorization 필드로 Bearer 토큰을 포함하여 제목, 내용, 유형, 이미지 공개여부를 입력받고 작성에 성공하면 성공처리를 합니다. 만약 작성에 실패하면 실패처리 됩니다. 인가 실패, 데이터베이스 에러, 데이터 유효성 검사 실패가 발생할 수 있습니다.

- method : **POST**
- URL : **/regist**

##### Request

###### Header

| name | description | required |
|---|:---:|:---:|
| Authorization | 인증에 사용될 Bearer 토큰 | O |

###### Request Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| qnaTitle | String | 제목 | O |
| qnaContents | String | 내용 | O |
| qnaCategory | String | 유형 | O |
| qnaPublic | Boolean | 공개여부 | O |
| qnaImageUrl | String | 이미지 | X |

###### Example

```bash
curl -v -X POST "http://localhost:4000/api/shop/qna/regist" \
 -H "Authorization: Bearer {JWT}" \
 -d "qnaTitle={qnaTitle}" \
 -d "qnaContents={qnaContents}"\
 -d "qnaCategory={qnaCategory}" \
 -d "qnaPublic={qnaPublic}" \
 -d "qnaImageUrl={qnaImageUrl}"
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| contentType | 반환하는 Response Body의 Content Type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 메세지 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
contentType: application/json;charset=UTF-8
{
  "code": "SU",
  "message": "Success.",
}
```

**응답 : 실패 (인증 실패)**
```bash
HTTP/1.1 401 Unauthorized
contentType: application/json;charset=UTF-8
{
  "code": "AF",
  "message": "Authentication Failed."
}
```

**응답 : 실패 (인가 실패)**
```bash
HTTP/1.1 403 Forbidden
contentType: application/json;charset=UTF-8
{
  "code": "AF",
  "message": "Authorization Failed."
}
```

**응답 : 실패 (데이터베이스 오류)**
```bash
HTTP/1.1 500 Internal Server Error
contentType: application/json;charset=UTF-8
{
  "code": "DBE",
  "message": "Database Error."
}
```

***

#### - 문의사항 게시물 답글 작성  
  
##### 설명

클라이언트로부터 Request Header의 Authorization 필드로 Bearer 토큰을 포함하여 문의사항 고유번호와 답글 내용을 입력받고 요청을 보내면 해당하는 문의 게시물의 답글이 작성됩니다. 만약 증가에 실패하면 실패처리를 합니다. 인가 실패, 인증 실패, 데이터베이스 에러가 발생할 수 있습니다.

- method : **POST**  
- URL : **/{qnaNumber}/comment**  

##### Request

###### Header

| name | description | required |
|---|:---:|:---:|
| Authorization | 인증에 사용될 Bearer 토큰 | O |

###### Path Variable

| name | type | description | required |
|---|:---:|:---:|:---:|
| qnaNumber | int | 문의사항 고유번호 | O |

###### Request Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| qnaComment | String | 답글 내용 | O |

###### Example

```bash
curl -v -X POST "http://localhost:4000/api/shop/qna/${qnaNumber}/comment" \
 -H "Authorization: Bearer {JWT}" \
 -d "qnaComment={qnaComment}"
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| contentType | 반환하는 Response Body의 Content Type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 메세지 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
contentType: application/json;charset=UTF-8
{
  "code": "SU",
  "message": "Success."
}
```

**응답 : 실패 (데이터 유효성 검사 실패)**
```bash
HTTP/1.1 400 Bad Request
contentType: application/json;charset=UTF-8
{
  "code": "VF",
  "message": "Validation Failed."
}
```

**응답 : 실패 (존재하지 않는 게시물)**
```bash
HTTP/1.1 400 Bad Request
contentType: application/json;charset=UTF-8
{
  "code": "NB",
  "message": "No Exist Board."
}
```

**응답 : 실패 (이미 작성된 답글)**
```bash
HTTP/1.1 400 Bad Request
{
  "code": "WC",
  "message": "Written Comment."
}
```

**응답 : 실패 (인증 실패)**
```bash
HTTP/1.1 401 Unauthorized
contentType: application/json;charset=UTF-8
{
  "code": "AF",
  "message": "Authentication Failed."
}
```

**응답 : 실패 (인가 실패)**
```bash
HTTP/1.1 403 Forbidden
contentType: application/json;charset=UTF-8
{
  "code": "AF",
  "message": "Authorization Failed."
}
```

**응답 : 실패 (권한 없음)**
```bash
HTTP/1.1 403 Forbidden
contentType: application/json;charset=UTF-8
{
  "code": "AF",
  "message": "Authorization Failed."
}
```

**응답 : 실패 (데이터베이스 오류)**
```bash
HTTP/1.1 500 Internal Server Error
contentType: application/json;charset=UTF-8
{
  "code": "DBE",
  "message": "Database Error."
}
```

***

#### - 문의사항 게시물 수정하기
  
##### 설명

클라이언트로부터 Request Header의 Authorization 필드로 Bearer 토큰을 포함하여 문의사항 고유번호, 제목, 내용을 입력받고 수정에 성공하면 성공처리를 합니다. 만약 수정에 실패하면 실패처리 됩니다. 인가 실패, 인증실패, 데이터베이스 에러, 데이터 유효성 검사 실패가 발생할 수 있습니다.

- method : **PUT**  
- URL : **/{qnaNumber}/modify**  

##### Request

###### Header

| name | description | required |
|---|:---:|:---:|
| Authorization | 인증에 사용될 Bearer 토큰 | O |

###### Path Variable

| name | type | description | required |
|---|:---:|:---:|:---:|
| qnaNumber | int | 문의사항 고유번호 | O |

###### Request Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| qnaTitle | String | 제목 | O |
| qnaContents | String | 내용 | O |
| qnaCategory | String | 유형 | O |
| qnaPublic | Boolean | 공개여부 | O |
| qnaImageUrl | String | 이미지 | X |

###### Example

```bash
curl -v -X PUT "http://localhost:4000/api/shop/qna/${qnaNumber}/modify" \
 -H "Authorization: Bearer {JWT}" \
 -d "qnaTitle={qnaTitle}" \
 -d "qnaContents={qnaContents} \
 -d "qnaCategory={qnaCategory} \
 -d "qnaPublic={qnaPublic} \
 -d "qnaImageUrl={qnaImageUrl} 
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| contentType | 반환하는 Response Body의 Content Type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 메세지 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
contentType: application/json;charset=UTF-8
{
  "code": "SU",
  "message": "Success.",
}
```

**응답 : 실패 (데이터 유효성 검사 실패)**
```bash
HTTP/1.1 400 Bad Request
contentType: application/json;charset=UTF-8
{
  "code": "VF",
  "message": "Validation Failed."
}
```

**응답 : 실패 (인가 실패)**
```bash
HTTP/1.1 403 Forbidden
contentType: application/json;charset=UTF-8
{
  "code": "AF",
  "message": "Authorization Failed."
}
```

**응답 : 실패 (존재하지 않는 게시물)**
```bash
HTTP/1.1 400 Bad Request
contentType: application/json;charset=UTF-8
{
  "code": "NB",
  "message": "No Exist Board."
}
```

**응답 : 실패 (답변 완료된 게시물)**
```bash
HTTP/1.1 400 Bad Request
contentType: application/json;charset=UTF-8
{
  "code": "WC",
  "message": "Written Comment."
}
```

**응답 : 실패 (권한 없음)**
```bash
HTTP/1.1 403 Forbidden
contentType: application/json;charset=UTF-8
{
  "code": "AF",
  "message": "Authorization Failed."
}
```

**응답 : 실패 (데이터베이스 오류)**
```bash
HTTP/1.1 500 Internal Server Error
contentType: application/json;charset=UTF-8
{
  "code": "DBE",
  "message": "Database Error."
}
```

***

#### - 문의사항 게시물 조회수 증가  
  
##### 설명

문의사항 고유번호를 입력받아 해당하는 문의사항 게시물의 조회수를 증가합니다. 만약 증가에 실패하면 실패처리를 합니다. 데이터베이스 에러가 발생할 수 있습니다.

- method : **PATCH**  
- URL : **/{qnaNumber}/increase-view-count**  

##### Request

###### Path Variable

| name | type | description | required |
|---|:---:|:---:|:---:|
| qnaNumber | int | 문의사항 고유번호 | O |

###### Example

```bash
curl -v -X PATCH "http://localhost:4000/api/shop/qna/${qnaNumber}/increase-view-count" \
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| contentType | 반환하는 Response Body의 Content Type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 메세지 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
contentType: application/json;charset=UTF-8
{
  "code": "SU",
  "message": "Success."
}
```

**응답 : 실패 (데이터 유효성 검사 실패)**
```bash
HTTP/1.1 400 Bad Request
contentType: application/json;charset=UTF-8
{
  "code": "VF",
  "message": "Validation Failed."
}
```

**응답 : 실패 (존재하지 않는 게시물)**
```bash
HTTP/1.1 400 Bad Request
contentType: application/json;charset=UTF-8
{
  "code": "NB",
  "message": "No Exist Board."
}
```

**응답 : 실패 (데이터베이스 오류)**
```bash
HTTP/1.1 500 Internal Server Error
contentType: application/json;charset=UTF-8
{
  "code": "DBE",
  "message": "Database Error."
}
```

***

#### - 문의사항 게시물 삭제하기
  
##### 설명

클라이언트로부터 Request Header의 Authorization 필드로 Bearer 토큰을 포함하여 문의사항 고유번호를 입력받고 요청을 보내면 해당하는 게시물이 삭제됩니다. 만약 삭제에 실패하면 실패처리를 합니다. 인가 실패, 인증 실패, 데이터베이스 에러가 발생할 수 있습니다.

- method : **DELETE**  
- URL : **/{qnaNumber}/delete**  

##### Request

###### Header

| name | description | required |
|---|:---:|:---:|
| Authorization | 인증에 사용될 Bearer 토큰 | O |

###### Path Variable

| name | type | description | required |
|---|:---:|:---:|:---:|
| qnaNumber | int | 문의사항 고유번호 | O |

###### Example

```bash
curl -v -X DELETE "http://localhost:4000/api/shop/qna/${qnaNumber}/delete" \
 -H "Authorization: Bearer {JWT}"
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| contentType | 반환하는 Response Body의 Content Type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 메세지 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
contentType: application/json;charset=UTF-8
{
  "code": "SU",
  "message": "Success."
}
```

**응답 : 실패 (데이터 유효성 검사 실패)**
```bash
HTTP/1.1 400 Bad Request
contentType: application/json;charset=UTF-8
{
  "code": "VF",
  "message": "Validation Failed."
}
```

**응답 : 실패 (존재하지 않는 게시물)**
```bash
HTTP/1.1 400 Bad Request
contentType: application/json;charset=UTF-8
{
  "code": "NB",
  "message": "No Exist Board."
}
```

**응답 : 실패 (인증 실패)**
```bash
HTTP/1.1 401 Unauthorized
contentType: application/json;charset=UTF-8
{
  "code": "AF",
  "message": "Authentication Failed."
}
```

**응답 : 실패 (인가 실패)**
```bash
HTTP/1.1 403 Forbidden
contentType: application/json;charset=UTF-8
{
  "code": "AF",
  "message": "Authorization Failed."
}
```

**응답 : 실패 (권한 없음)**
```bash
HTTP/1.1 403 Forbidden
contentType: application/json;charset=UTF-8
{
  "code": "AF",
  "message": "Authorization Failed."
}
```

**응답 : 실패 (데이터베이스 오류)**
```bash
HTTP/1.1 500 Internal Server Error
contentType: application/json;charset=UTF-8
{ 
  "code": "DBE",
  "message": "Database Error."
}
```

***

<h2 style='background-color: rgba(55, 55, 55, 0.2); text-align: center'> Faq 모듈 </h2>  
  
- url : /api/shop/faq

#### - 자주하는 질문 리스트 보기

##### 설명

자주하는 질문 페이지에서 작성일 기준 내림차순으로 공지사항 리스트를 반환합니다. 만약 불러오기에 실패하면 실패처리를 합니다. 데이터베이스 에러가 발생할 수 있습니다.

- method : **GET**  
- URL : **/list**  

##### Request

###### Example

```bash
curl -v -X GET "http://localhost:4000/api/shop/faq/list" \
 -H "Authorization: Bearer {JWT}"
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| contentType | 반환하는 Response Body의 Content Type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 메세지 | O |
| faqList | faqListItem[] | 자주하는 질문 리스트 | O |

**faqListItem**
| name | type | description | required |
|---|:---:|:---:|:---:|
| faqNumber | int | 자주하는 질문 고유번호 | O |
| faqQuestion | String | 제목 | O |
| faqAnswer | String | 대답 | O |
| faqCategory | String | 유형 | O |
| faqDate | String | 작성일</br>(yyyy.mm.dd 형태) | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
contentType: application/json;charset=UTF-8
{
  "code": "SU",
  "message": "Success.",
  "faqList": [
    {
      "faqNumber": 1,
      "faqQuestion": "제목",
      "faqAnswer": "내용",
      "faqCategory": "주문|배송",
      "faqDate": "2024.08.04"
    }, ...
  ]
}
```

**응답 : 실패 (데이터베이스 오류)**
```bash
HTTP/1.1 500 Internal Server Error
contentType: application/json;charset=UTF-8
{
  "code": "DBE",
  "message": "Database Error."
}
```

***

#### - 자주하는 질문 작성하기
  
##### 설명

클라이언트로부터 Request Header의 Authorization 필드로 Bearer 토큰을 포함하여 제목, 내용, 카테고리를 입력받고 작성에 성공하면 성공처리 합니다. 만약 작성에 실패하면 실패처리를 합니다. 인가 실패, 인증 실패, 데이터베이스 에러가 발생할 수 있습니다.

- method : **POST**  
- URL : **/regist**  

##### Request

###### Header

| name | description | required |
|---|:---:|:---:|
| Authorization | 인증에 사용될 Bearer 토큰 | O |

###### Request Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| faqQuestion | String | 제목 | O |
| faqAnswer | String | 내용 | O |
| faqCategory | String | 유형 | O |

###### Example

```bash
curl -v -X POST "http://localhost:4000/api/shop/faq/regist" \
 -H "Authorization: Bearer {JWT}" \
 -d "faqQuestion={faqQuestion}" \
 -d "faqAnswer={faqAnswer}" \
 -d "faqCategory={faqCategory}"
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| contentType | 반환하는 Response Body의 Content Type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 메세지 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
contentType: application/json;charset=UTF-8
{
  "code": "SU",
  "message": "Success.",
}
```

**응답 : 실패 (인증 실패)**
```bash
HTTP/1.1 401 Unauthorized
contentType: application/json;charset=UTF-8
{
  "code": "AF",
  "message": "Authentication Failed."
}
```

**응답 : 실패 (인가 실패)**
```bash
HTTP/1.1 403 Forbidden
contentType: application/json;charset=UTF-8
{
  "code": "AF",
  "message": "Authorization Failed."
}
```

**응답 : 실패 (데이터베이스 오류)**
```bash
HTTP/1.1 500 Internal Server Error
contentType: application/json;charset=UTF-8
{
  "code": "DBE",
  "message": "Database Error."
}
```

***

#### - 자주하는 질문 수정하기
  
##### 설명

클라이언트로부터 Request Header의 Authorization 필드로 Bearer 토큰을 포함하여 제목, 내용,  유형을 입력받고 수정에 성공하면 성공처리 합니다. 만약 수정에 실패하면 실패처리를 합니다. 인가 실패, 인증 실패, 데이터베이스 에러, 데이터 유효성 검사 실패가 발생할 수 있습니다.

- method : **PUT**  
- URL : **/{faqNumber}/modify**  

##### Request

###### Header

| name | description | required |
|---|:---:|:---:|
| Authorization | 인증에 사용될 Bearer 토큰 | O |

###### Path Variable

| name | type | description | required |
|---|:---:|:---:|:---:|
| faqNumber | int | 자주하는 질문 고유번호 | O |

###### Request Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| faqQuestion | String | 제목 | O |
| faqAnswer | String | 내용 | O |
| faqCategory | String | 유형 | O |


###### Example

```bash
curl -v -X PUT "http://localhost:4000/api/shop/faq/${faqNumber}/modify" \
 -H "Authorization: Bearer {JWT}" \
 -d "faqQuestion={faqQuestion}" \
 -d "faqAnswer={faqAnswer}" \
 -d "faqCategory={faqCategory}"
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| contentType | 반환하는 Response Body의 Content Type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 메세지 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
contentType: application/json;charset=UTF-8
{
  "code": "SU",
  "message": "Success.",
}
```

**응답 : 실패 (데이터 유효성 검사 실패)**
```bash
HTTP/1.1 400 Bad Request
contentType: application/json;charset=UTF-8
{
  "code": "VF",
  "message": "Validation Failed."
}
```

**응답 : 실패 (존재하지 않는 게시물)**
```bash
HTTP/1.1 400 Bad Request
contentType: application/json;charset=UTF-8
{
  "code": "NB",
  "message": "No Exist Board."
}
```

**응답 : 실패 (인가 실패)**
```bash
HTTP/1.1 403 Forbidden
contentType: application/json;charset=UTF-8
{
  "code": "AF",
  "message": "Authorization Failed."
}
```

**응답 : 실패 (인증 실패)**
```bash
HTTP/1.1 401 Unauthorized
contentType: application/json;charset=UTF-8
{
  "code": "AF",
  "message": "Authentication Failed."
}
```

**응답 : 실패 (데이터베이스 오류)**
```bash
HTTP/1.1 500 Internal Server Error
contentType: application/json;charset=UTF-8
{
  "code": "DBE",
  "message": "Database Error."
}
```

***

#### - 자주하는 질문 삭제하기
  
##### 설명

클라이언트로부터 Request Header의 Authorization 필드로 Bearer 토큰을 포함하여 자주하는 질문 고유번호를 입력받고 요청을 보내면 해당하는 게시물이 삭제됩니다. 만약 삭제에 실패하면 실패처리를 합니다. 인가 실패, 인증 실패, 데이터베이스 에러가 발생할 수 있습니다.

- method : **DELETE**  
- URL : **/{faqNumber}/delete**  

##### Request

###### Header

| name | description | required |
|---|:---:|:---:|
| Authorization | 인증에 사용될 Bearer 토큰 | O |

###### Path Variable

| name | type | description | required |
|---|:---:|:---:|:---:|
| faqNumber | int | 자주하는 질문 고유번호 | O |

###### Example

```bash
curl -v -X DELETE "http://localhost:4000/api/shop/qna/${faqNumber}/delete" \
 -H "Authorization: Bearer {JWT}"
```

##### Response

###### Header

| name | description | required |
|---|:---:|:---:|
| contentType | 반환하는 Response Body의 Content Type (application/json) | O |

###### Response Body

| name | type | description | required |
|---|:---:|:---:|:---:|
| code | String | 결과 코드 | O |
| message | String | 결과 메세지 | O |

###### Example

**응답 성공**
```bash
HTTP/1.1 200 OK
contentType: application/json;charset=UTF-8
{
  "code": "SU",
  "message": "Success."
}
```

**응답 : 실패 (데이터 유효성 검사 실패)**
```bash
HTTP/1.1 400 Bad Request
contentType: application/json;charset=UTF-8
{
  "code": "VF",
  "message": "Validation Failed."
}
```

**응답 : 실패 (존재하지 않는 게시물)**
```bash
HTTP/1.1 400 Bad Request
contentType: application/json;charset=UTF-8
{
  "code": "NB",
  "message": "No Exist Board."
}
```

**응답 : 실패 (인증 실패)**
```bash
HTTP/1.1 401 Unauthorized
contentType: application/json;charset=UTF-8
{
  "code": "AF",
  "message": "Authentication Failed."
}
```

**응답 : 실패 (인가 실패)**
```bash
HTTP/1.1 403 Forbidden
contentType: application/json;charset=UTF-8
{
  "code": "AF",
  "message": "Authorization Failed."
}
```

**응답 : 실패 (권한 없음)**
```bash
HTTP/1.1 403 Forbidden
contentType: application/json;charset=UTF-8
{
  "code": "AF",
  "message": "Authorization Failed."
}
```

**응답 : 실패 (데이터베이스 오류)**
```bash
HTTP/1.1 500 Internal Server Error
contentType: application/json;charset=UTF-8
{ 
  "code": "DBE",
  "message": "Database Error."
}
```

***

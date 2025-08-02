# 📍 도란도란 ( DoranDoran )

**🗓 프로젝트 기간: 2025.3 ~ 2025.8**

책 읽는 문화를 함께 만들어가는 독서 커뮤니티 Android 애플리케이션입니다.
독서에 대한 흥미를 유도하고, 책을 좋아하는 사람들이 소통하며 함께 성장할 수 있는 공간을 제공합니다.

## 📌 주요 기능
- **도서 리뷰 작성 및 소통**: 읽은 책에 대한 리뷰를 작성하고, 다른 독자들과 댓글을 통해 자유롭게 의견을 나눌 수 있습니다.
- **주제별 토론 기능**: 서책의 주제나 메시지를 중심으로 다양한 시각을 공유하며 깊이 있는 토론을 할 수 있습니다.
- **인상 깊은 문구 공유**: 마음을 울린 책 속 문장을 기록하고 공유함으로써 공감과 영감을 나눌 수 있습니다.


## 🛠 기술 스택
- **언어**: Kotlin
- **프레임워크**: Jetpack Compose
- **아키텍처**: 클린 아키텍처 (Clean Architecture)
- **버전 관리**: Git, GitHub


## 📂 프로젝트 구조
```
📦 DoranDoran-Android
│
├── 📁 app                     # 애플리케이션 설정 및 진입 지점
├── 📁 core_ui                # 공통 UI 구성 요소
│   ├── 📁 component           # 재사용 가능한 UI 컴포넌트
│   └── 📁 theme               # 테마 및 스타일 정의
│
├── 📁 data                   # Data Layer
│   ├── 📁 auth                # 인증 관련 데이터 소스
│   ├── 📁 datasource          # 인터페이스 정의
│   ├── 📁 datasourceimpl      # 실제 데이터 소스 구현체
│   ├── 📁 dto                 # 데이터 전송 객체 (Request/Response)
│   ├── 📁 mapper              # DTO ↔ Entity 변환 로직
│   ├── 📁 module              # DI 모듈 설정
│   ├── 📁 repositoryimpl      # Repository 구현체
│   └── 📁 service             # API 서비스 인터페이스
│
├── 📁 domain                 # Domain Layer
│   ├── 📁 entity              # 비즈니스 모델 정의
│   ├── 📁 repository          # Repository 인터페이스
│   └── 📁 usecase             # 도메인 로직 (UseCases)
│
├── 📁 presentation           # Presentation Layer (화면 UI 구성)
│   ├── 📁 auth                # 로그인 / 회원가입 화면
│   ├── 📁 discuss             # 책 주제별 토론 화면
│   ├── 📁 example             # 예제용 샘플 화면
│   ├── 📁 home                # 홈 화면
│   ├── 📁 main                # 메인 진입 화면
│   ├── 📁 mypage              # 마이페이지 화면
│   ├── 📁 navigator           # 네비게이션 처리
│   └── 📁 review              # 도서 리뷰 화면
│
├── 📁 util                   # 공통 유틸 함수/클래스 모음
│
└── 📄 build.gradle.kts
```

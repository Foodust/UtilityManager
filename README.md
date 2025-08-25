# UtilityManager

마인크래프트 플러그인 개발을 위한 종합 유틸리티 라이브러리입니다.  
아이템 생성, 한글 조사 처리, 메시지 전송, 디스플레이 엔티티 관리 등 플러그인 개발에 자주 사용되는 기능들을 모듈화하여 제공합니다.

## 주요 기능

- **ItemModule**: 아이템 생성, 비교, 검증
- **HangulModule**: 한국어 조사(이/가, 을/를, 은/는, 으로/로) 자동 처리
- **MessageModule**: 플레이어 메시지, 타이틀, 액션바 전송
- **DisplayModule**: ItemDisplay, BlockDisplay, TextDisplay 생성
- **ConfigModule**: YAML 설정 파일 관리
- **TaskModule**: 작업 스케줄링

## 설치 방법

### Gradle (권장)

`build.gradle` 또는 `build.gradle.kts` 파일에 다음을 추가하세요:

#### Groovy DSL (build.gradle)
```gradle
repositories {
    maven {
        name = "jitpack"
        url = "https://jitpack.io"
    }
}

dependencies {
    implementation 'com.github.Foodust:UtilityManager:VERSION'
}
```

#### Kotlin DSL (build.gradle.kts)
```kotlin
repositories {
    maven {
        name = "jitpack"
        url = uri("https://jitpack.io")
    }
}

dependencies {
    implementation("com.github.Foodust:UtilityManager:VERSION")
}
```

### Maven
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>com.github.Foodust</groupId>
        <artifactId>UtilityManager</artifactId>
        <version>VERSION</version>
    </dependency>
</dependencies>
```

## 사용 방법

### 기본 설정

메인 플러그인 클래스에서 UtilityManager를 초기화하세요:

```java
public class YourPlugin extends JavaPlugin {
    private MessageModule messageModule;
    private ItemModule itemModule;
    private HangulModule hangulModule;
    
    @Override
    public void onEnable() {
        // 모듈 초기화
        this.messageModule = new MessageModule(this);
        this.itemModule = new ItemModule();
        this.hangulModule = new HangulModule();
        
        // 메시지 prefix 설정 (선택사항)
        messageModule.setPrefix("§7[§6YourPlugin§7] §f");
    }
}
```

### ItemModule 사용 예제

```java
// 기본 아이템 생성
ItemStack sword = itemModule.createItem(Material.DIAMOND_SWORD, 1);

// 이름이 있는 아이템 생성
ItemStack namedSword = itemModule.createItem(
    Material.DIAMOND_SWORD, 
    "§6전설의 검", 
    1
);

// 설명이 있는 아이템 생성
List<String> lore = Arrays.asList(
    "§7공격력: §c+10",
    "§7내구도: §a100%"
);
ItemStack detailedSword = itemModule.createItem(
    Material.DIAMOND_SWORD,
    "§6전설의 검",
    lore,
    1
);

// CustomModelData가 있는 커스텀 아이템 생성
ItemStack customSword = itemModule.createCustomItem(
    Material.DIAMOND_SWORD,
    "§6커스텀 검",
    lore,
    12345,  // CustomModelData
    1
);

// 아이템 비교
boolean isSimilar = itemModule.isSimilarIgnoreAmount(item1, item2);
boolean isEmpty = itemModule.isEmpty(itemStack);
```

### HangulModule 사용 예제

```java
HangulModule hangul = new HangulModule();

// 자동 조사 붙이기
String result1 = hangul.getJosa("사과", HangulModule.Josa.EI_GA);  // "사과가"
String result2 = hangul.getJosa("바나나", HangulModule.Josa.EL_LL);  // "바나나를"
String result3 = hangul.getJosa("책", HangulModule.Josa.EN_NN);     // "책은"
String result4 = hangul.getJosa("집", HangulModule.Josa.EU_RO);     // "집으로"

// 한글 검증
boolean isHangul = hangul.isHangul("안녕하세요");  // true
boolean hasConsonant = hangul.hasLastConsonant("받침");  // true

// 조사 제거
String removed = hangul.removeJosa("사과를");  // "사과"
```

### MessageModule 사용 예제

```java
MessageModule message = new MessageModule(this);

// 일반 메시지 전송
message.sendPlayer(player, "안녕하세요!");

// 한글 조사와 함께 메시지 전송
message.sendPlayer(player, "사과", HangulModule.Josa.EL_LL, "먹었습니다.");
// 결과: "[Prefix] 사과를 먹었습니다."

// MiniMessage 포맷으로 메시지 전송
message.sendPlayerC(player, "<red>경고!</red> <yellow>주의하세요.</yellow>");

// 타이틀 전송
message.sendTitle(player, "§6환영합니다!", "§7서버에 오신 것을 환영합니다.", 1, 3, 1);

// 액션바 전송
message.sendPlayerActionBar(player, "§e경험치: §610/100");

// 모든 플레이어에게 메시지 전송
message.sendAllPlayer("서버 점검이 시작됩니다.");
```

### DisplayModule 사용 예제

```java
DisplayModule display = new DisplayModule();

// 아이템 디스플레이 생성
ItemDisplay itemDisplay = display.makeItemDisplay(
    world, 
    location, 
    new ItemStack(Material.DIAMOND), 
    2.0  // 크기
);

// 블록 디스플레이 생성
BlockDisplay blockDisplay = display.makeBlockDisplay(
    world,
    location,
    Material.STONE,
    1.5
);

// 텍스트 디스플레이 생성
TextDisplay textDisplay = display.makeTextDisplay(
    world,
    location,
    "§6안내문",
    1.0
);

// Billboard 설정과 함께 생성
TextDisplay billboardText = display.makeTextDisplay(
    world,
    location,
    "§c경고!",
    2.0,
    Display.Billboard.CENTER
);

// 개별 축 크기 설정
ItemDisplay scaledItem = display.makeItemDisplay(
    entity,
    location,
    itemStack,
    2.0f, 1.0f, 2.0f  // X, Y, Z 축별 크기
);
```

### ConfigModule 사용 예제

```java
ConfigModule config = new ConfigModule();

// 설정 파일 로드
FileConfiguration playerConfig = config.getConfig("players.yml");

// 설정 값 읽기
String playerName = playerConfig.getString("player.name", "Unknown");
int playerLevel = playerConfig.getInt("player.level", 1);

// 설정 값 변경
playerConfig.set("player.name", "NewName");
playerConfig.set("player.level", 10);

// 설정 파일 저장
boolean success = config.saveConfig(playerConfig, "players.yml");

// 설정 파일 존재 확인
if (config.configExists("config.yml")) {
    // 파일이 존재함
}

// 설정 파일 백업
config.backupConfig("important-config.yml");
```

### TaskModule 사용 예제

```java
TaskModule task = new TaskModule(this);

// 즉시 실행
task.runBukkitTask(() -> {
    Bukkit.getLogger().info("즉시 실행되는 작업");
});

// 지연 실행 (20틱 = 1초 후)
task.runBukkitTaskLater(() -> {
    Bukkit.broadcastMessage("1초 후 실행됨");
}, 20L);

// 반복 실행 (1초 후 시작, 5초마다 반복)
BukkitTask repeatingTask = task.runBukkitTaskTimer(() -> {
    Bukkit.broadcastMessage("5초마다 실행");
}, 20L, 100L);

// 비동기 작업 실행
task.runBukkitTaskAsync(() -> {
    // 무거운 작업 (파일 I/O, 데이터베이스 쿼리 등)
    Bukkit.getLogger().info("비동기 작업 완료");
});

// 작업 취소
task.cancelBukkitTask(repeatingTask);

// 모든 작업 취소
task.cancelAllTasks();
```

## 의존성

이 라이브러리는 다음 라이브러리들을 사용합니다:

- **Paper API** (또는 Bukkit/Spigot API)
- **Adventure API** - 고급 텍스트 포맷팅

## 호환성

- **Minecraft**: 1.19.4+
- **Java**: 17+
- **Paper/Spigot**: 최신 버전 권장

## 라이선스

이 프로젝트는 MIT 라이선스 하에 배포됩니다. 자세한 내용은 [LICENSE](LICENSE) 파일을 참조하세요.

## 기여하기

버그 리포트, 기능 제안, 풀 리퀘스트를 환영합니다!

1. 이 저장소를 Fork 하세요
2. 새 브랜치를 생성하세요 (`git checkout -b feature/AmazingFeature`)
3. 변경사항을 커밋하세요 (`git commit -m 'Add some AmazingFeature'`)
4. 브랜치에 Push 하세요 (`git push origin feature/AmazingFeature`)
5. Pull Request를 열어주세요

## 문의

프로젝트에 대한 질문이나 제안이 있으시면 Issues를 통해 연락해 주세요.

# Insight Hub iOS

## Requirements

- Android Studio
- Kotlin 2 or later
- Homebrew
  - [ktlint](https://github.com/pinterest/ktlint): A code formatter dedicated for Swift

## Supports

- SDK 30 or higher

## Development

### Setup

> [!CAUTION]
> If you want to communicate to API for development purpose, you need to follow [this instruction](https://github.com/hukurou0/insight-hub/tree/master/api) to start the localhost.

1. Clone this repository

    ```shell
    git clone https://github.com/hukurou0/insight-hub-android.git
    ```

2. Run setup command

    This command sets up pre-commit hook to run `ktlint` before you commit.

    ```shell
    make setup
    ```

#!/bin/bash

# Colors
RED='\033[0;31m'
GREEN='\033[0;32m'
NC='\033[0m'

prompt_install() {
    read -p "$1 is not installed. Do you want to install it? (y/n): " choice
    if [[ "$choice" == "y" || "$choice" == "Y" ]]; then
        $2
    else
        exit 1
    fi
}

# Check for Homebrew
if ! command -v brew &> /dev/null; then
    prompt_install "Homebrew" '/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"'
fi

# Check for ktlint
if ! command -v ktlint &> /dev/null; then
    prompt_install "ktlint" 'brew install ktlint'
fi

ktlint installGitPreCommitHook
chmod +x $PWD/.git/hooks/pre-commit

echo -e "${GREEN}Setup complete.${NC}"
exit 0
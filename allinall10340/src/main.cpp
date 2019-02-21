#include <iostream>
#include <string>

using namespace std;

int main() {

    string sstring, text;
    while(cin>>sstring>>text) {

        size_t sspos = 0;
        for(size_t i = 0; i < text.size(); i++) {
            if (sspos < sstring.size() && sstring[sspos] == text[i]) {
                sspos++;
            }
        }
        if (sspos == sstring.size()) {
            cout << "Yes" << endl;
        } else {
            cout << "No" << endl;
        }
    }
    return 0;
}


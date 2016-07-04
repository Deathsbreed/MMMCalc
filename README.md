MMMCalc
=======
MMMCalc is a program I made after school one day after having to learn about Mean, Median, and Mode for the thousandth time. So instead of doing my actual homework I decided to make this program which would do it for me. Of course, the program with this is that your teachers often ask you to show your work. Well, fear not! For MMMCalc shows you **its** work. After it does its calculations, if you turn on _verbose mode_ (by using the `-v` argument) it will show you how MMMCalc got to that answer that it's giving you. After going through Mean, Median, and Mode (the reason why this program is called **MMM**Calc) I decided to add a few other basic statistical properties to it.

I hope this helps someone to make their millionth time of doing Mean, Median, and Mode in school a little less of a pain in the ass.

### Compiling
MMMCalc uses [CMake](http://cmake.org/) to compile. Please install the C development tools and CMake (along with whichever build script you plan on having CMake generate) in order to run this command.
```bash
$ cd build/
$ cmake ..
$ make
```

### Contributing
To contribute, simply open a pull request, however, be aware that this code is licensed under a GNU GPLv3 and so will yours.

### License
All code in this repository is under the [GNU GPLv3](/LICENSE).

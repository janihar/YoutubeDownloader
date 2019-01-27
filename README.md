# YoutubeDownloader (DEMO : https://www.youtube.com/watch?v=UA1YvEqZhPA)
Simple youtube downloader with GUI to search and download video file only. You are not able to download every single video there is, because
this version do not include cipher algorithm. It took a while reverse youtube.. Cheers to this guy (https://tyrrrz.me/Blog/Reverse-engineering-YouTube)
It explained very well how youtube works. I basically have the idea how I would break cipher of the videos but the part I cannot catch is 
that what I am looking for player.js file. There is a reverse, swap and slice functions that could break cipher. VEVO and such a known videos 
use cipher that prevents this program to download them.. 

Things I learned:

- Usage of Youtube API

- JavaFX, SceneBuilder

- Apache Maven

- Java http response

- Some Regular Expressions

Things to develop/fix:

- A better looking GUI

- A bug with filedirectory chooser. Currently if you click rapidly the place you want to put your file it freezes.

- Also there is tiny change that downloadable link is incorrect, because some incorrect use of regular expressions.

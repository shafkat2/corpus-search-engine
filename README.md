# web-search-engine
University of Windsor - COMP8547-4-R-2019F Advanced Computing Concepts - Final Project - Group 14.

The application uses java to build a search engine.

The main features of the search engine are
    -> Crawler[1]
    -> List the websites
    -> Suggested words
    -> cache Data
    
![image](https://user-images.githubusercontent.com/41389917/126972623-ceb3039c-4f63-4d26-9836-96992c81fd99.png)

    
    # Crawler[1]: was built using regex and jsoup, we it crawls the website link that is provided and saves the websites in a .htm file in the specified location.
        -> Regex
        -> Jsoup
   ![image](https://user-images.githubusercontent.com/41389917/126972403-3292e4eb-2331-416d-9cec-c75f36a435ea.png)
   
    # List the websites: uses a trie to hold the words  in the crawled files and stores the frequence of the words present in hashmap to suggest the most relevant page
        -> Trie
        -> Hashmap
        -> Array
   ![image](https://user-images.githubusercontent.com/41389917/126972484-da6a1662-e7f3-48ad-8dde-48d06bdb3646.png)

    # Suggested words: The frequency of the words are stored in a hashmap and if the words that was searched does not exist in the crawled pages then few words are suggested based on the simiarlity
        -> Edit Distance(KMP)
        -> quick Sort
   ![image](https://user-images.githubusercontent.com/41389917/126972537-78576acb-4a49-4cc1-b3ec-e7b05d20c147.png)
   
     # cache Data: The recently searched data is stored in a LRU cache, LRU cache keeps the recently searched value at the top, also if the user searches for the same value that was searched before, cahce data is turned making the search for website pages even faster
        -> Hashmap
        -> Queue
        -> Array
   ![image](https://user-images.githubusercontent.com/41389917/126972580-e0d82a57-5f13-47e1-a266-de4c6b3ce875.png)







[1]D. Lam, datqlam/web-search-engine. 2020. Accessed: Jul. 26, 2021. [Online]. Available: https://github.com/datqlam/web-search-engine

package com.papaxiong.controller;

import com.papaxiong.support.Wrapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @author zhaoqi
 * @since 2021-04-02
 */

@RestController
public class SensitiveController {





    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    class TrieNode{
        private Character word;


        private Boolean isEnd=false;


        private HashMap<Character,TrieNode> child;
    }



    @Getter
    @Setter
    class TrieTree {


        //根节点的Map
        HashMap<Character,TrieNode> rootNodeMap;


        public void addWord(String word) {


            char[] chars = word.toCharArray();
            //构建树
            if (Objects.isNull(rootNodeMap)) {
                rootNodeMap = new HashMap<>();
            }


            HashMap<Character,TrieNode> nodeMap = rootNodeMap;


            for (int i = 0; i < chars.length; i++) {
                char aChar = chars[i];
                TrieNode trieNode = nodeMap.get(Character.valueOf(aChar));
                if (!Objects.isNull(trieNode)) {
                    //往下继续找
                    nodeMap = trieNode.child;
                    if (i == chars.length - 1) {
                        trieNode.setIsEnd(true);
                    }
                    continue;
                }
                //塞入这个节点
                trieNode = new TrieNode();
                nodeMap.put(aChar, trieNode);
                trieNode.setWord(aChar);
                trieNode.setChild(new HashMap<>());
                nodeMap = trieNode.child;
                if (i == chars.length - 1) {
                    trieNode.setIsEnd(true);
                }
            }

        }

        public List<String> checkDoc(String doc){

            List<String> sensitiveWords = new LinkedList<>();

            int startIndex = 0;
            int endIndex = 1;



            String word;
            while (endIndex <= doc.length()) {
                word = doc.substring(0, endIndex);
                if (!checkWord(word)) {
                    sensitiveWords.add(word);
                    //校验不通过的话  只动后指针   因为有可能匹配多个敏感字
                    endIndex++;
                    continue;
                }
                //如果校验通过 不会再存在这个前缀了 所以前指针也可以动
                startIndex++;
                endIndex = startIndex + 1;
            }

            return sensitiveWords;
        }


        public Boolean checkWord(String word){

            char[] chars = word.toCharArray();


            HashMap<Character,TrieNode> nodeHashMap=rootNodeMap;


            for (int i = 0; i < chars.length; i++) {
                char aChar = chars[i];
                if (!nodeHashMap.containsKey(Character.valueOf(aChar))) {
                    return true;
                }
                //包含的话进到下一层
                TrieNode trieNode = nodeHashMap.get(Character.valueOf(aChar));
                if (trieNode.getIsEnd() && i == chars.length - 1) {
                    //最后一个节点
                    return false;
                }
                nodeHashMap = trieNode.child;
                if(nodeHashMap==null){
                    break;
                }
            }
            return true;

        }
    }


    @Bean
    public TrieTree trieTree(){
        TrieTree trieTree = new TrieTree();
        trieTree.addWord("王八羔子");
        trieTree.addWord("王八");
        trieTree.addWord("王八蛋");
        trieTree.addWord("大坏蛋");
        trieTree.addWord("大猪蹄子");
        trieTree.addWord("讨厌");
        return trieTree;
    }

    @Autowired
    private TrieTree trieTree;


    
    @RequestMapping("/sensitive/check")
    public Wrapper<String> checkSensitive(String word) {
        List<String> strings = trieTree.checkDoc(word);
        System.out.println(strings);
        return Wrapper.ok("ok");

    }
}

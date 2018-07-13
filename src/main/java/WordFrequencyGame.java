import java.util.*;

/**
 * Created by jxzhong on 2018/5/22.
 */
public class WordFrequencyGame {
    public String getResult(String inputStr) {

        if (inputStr.split("\\s+").length == 1) {
            return inputStr + " 1";
        } else {
            Map<String, List<Input>> map = formatInput(inputStr);

            List<Word> worldList = getWordList(map);

            worldList.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

            return getResultView(worldList);
        }
    }

    private String getResultView(List<Word> list) {
        StringJoiner joiner = new StringJoiner("\n");
        for (Word w : list) {
            joiner.add(w.getValue() + " " + w.getWordCount());
        }
        return joiner.toString();
    }

    private List<Word> getWordList(Map<String, List<Input>> map) {
        List<Word> list = new ArrayList<>();
        for (Map.Entry<String, List<Input>> entry : map.entrySet()) {
            list.add(new Word(entry.getKey(), entry.getValue().size()));
        }
        return list;
    }

    private Map<String, List<Input>> formatInput(String inputStr) {
        String[] arr = inputStr.split("\\s+");

        List<Input> inputList = new ArrayList<>();
        for (String s : arr) {
            inputList.add(new Input(s));
        }

        return getListMap(inputList);
    }

    private Map<String, List<Input>> getListMap(List<Input> inputList) {
        Map<String, List<Input>> map = new HashMap<>();
        for (Input input : inputList) {
            if (!map.containsKey(input.getValue())) {
                ArrayList arr = new ArrayList<>();
                arr.add(input);
                map.put(input.getValue(), arr);
            } else {
                map.get(input.getValue()).add(input);
            }
        }
        return map;
    }
}

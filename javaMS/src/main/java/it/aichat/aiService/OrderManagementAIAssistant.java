/* Cesare Mauri - Ai MLM Team (C) 2024 */
package it.aichat.aiService;

import java.util.Set;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class OrderManagementAIAssistant {
  private final ChatModel chatClient;

  public OrderManagementAIAssistant(@Qualifier("ollamaChatModel") ChatModel chatClient) {
    this.chatClient = chatClient;
  }

  public ChatResponse callChatClient(Set<String> functionNames, String promptString) {
    Prompt prompt =
        new Prompt(promptString, OpenAiChatOptions.builder().withFunctions(functionNames).build());
    return chatClient.call(prompt);
  }
}

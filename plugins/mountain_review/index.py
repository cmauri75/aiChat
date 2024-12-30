from cat.log import log
from cat.mad_hatter.decorators import hook


@hook
def agent_fast_reply(reply, cat):
    # intercetto il prompt in input
    user_mess = cat.working_memory.user_message_json.text
    log(f"user_mess: {user_mess}", level='INFO')

    # Interagisco direttamente col LLM
    new_prompt = "Racconta una barzelletta sugli sport di montagna"
    barz = cat.llm(new_prompt)

    log(f"barz: {barz}", level='INFO')

    original_res = cat.llm(user_mess)
    log(f"original_res: {original_res}", level='INFO')

#
#
# # Calcolo il sentiment
# sentiment = cat.classify(user_mess, labels=["positive", "negative"])
# print(f"*-->4: {sentiment}")
#
# sentimentMsg = "Non calcolato"
# if sentiment is not None:
#     sentimentMsg = sentiment
#
# reply["output"] = f"* Hai detto: {user_mess} è un messaggio: - {sentimentMsg}\n * {barz}\n * {original_res}"
    reply["output"] = "a"
return reply

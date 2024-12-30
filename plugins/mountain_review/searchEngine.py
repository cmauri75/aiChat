from cat.log import log
from cat.mad_hatter.decorators import hook


@hook
def agent_fast_reply(reply, cat):
    # intercetto il prompt in input
    user_mess = cat.working_memory.user_message_json.text

#1. call SE
#2. insert in prompt
    #3. ask llm for response

    return reply

# Startup cheshrire via docker compose up
docker compose up

Access console on http://localhost:1865/admin/
* admin pwd: Xn39#)=wIb3+

## Use local LLM --> ollama. In un LLM entra testo ed esce testo
* install ollama
Ora ho diversi modelli a disposizione: https://ollama.com/search
* run: `ollama run llama3.2:3b`
* configure on cheshire: Model: llama3.2, Base url: http://host.docker.internal:11434

Altrimenti potevi usare ae Regolo che ti offre vari LLM online

## embedder: è un language model ridotto dove entra testo ed esce un vettore spaziale multidimenzionale che lo rappresenta geometricamente
* use value="sentence-transformers/paraphrase-multilingual-MiniLM-L12-v2"

Frasi con significato simile sono vicine


## Col bottone in basso a sinistra posso caricare fonti informative addizionali, quali pdf o url

## Configurazione parametri: cat-advanced-tool

Posso modificare vari parametri ma anche dare un prompt iniziale che definisce il comportamento del LLM

Nella fattispece ho configurato il prompt prefix con: "Sei un sistema di assistenza serio per recuperare consigli sui prodotti outdoor censiti su mountainreview.it"
Questo ridetermina completamente le risposte.

Inoltre ho impostato il linguaggio italiano

## nuovo plugin
Basta creare una directory col descrittore json e un file python che contiene la logica. Vedi esempio

## Integrazione motore di ricerca
- posso usare pacchetti python che lanciano crowler (python google search scrape)
- oppure ci sono plugin che si basano su questi crowler, ae: duckcatgo o coriouscat
- Altrimenti ho servizi pronti tipo Brave search api o serpapi.com 

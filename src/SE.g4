grammar SE;

start: 'StateElimination' OPENC 'FileName' COLON FILENAME SEMICOLON eMode;

eMode: 'Mode' COLON ('default' | 'steps') SEMICOLON type;

type: 'Type' COLON ('manual' SEMICOLON ordering | 'automatic' SEMICOLON CLOSEC);

ordering: 'Ordering' COLON (NUM (COMMA NUM)* SEMICOLON CLOSEC);

OPENC: '{';
CLOSEC: '}';
COLON: ':';
SEMICOLON: ';';
COMMA: ',';
NUM:    [0-9]+;
FILENAME: [a-z0-9]+ '.dot';

WS:   (' ' | '\t' | '\r'| '\n')+ -> channel(HIDDEN);
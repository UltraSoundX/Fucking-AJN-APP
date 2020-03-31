package org.jsoup.parser;

enum TokeniserState {
    Data {
        /* access modifiers changed from: 0000 */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            switch (characterReader.current()) {
                case 0:
                    tokeniser.error((TokeniserState) this);
                    tokeniser.emit(characterReader.consume());
                    return;
                case '&':
                    tokeniser.advanceTransition(CharacterReferenceInData);
                    return;
                case '<':
                    tokeniser.advanceTransition(TagOpen);
                    return;
                case 65535:
                    tokeniser.emit((Token) new EOF());
                    return;
                default:
                    tokeniser.emit(characterReader.consumeToAny('&', '<', TokeniserState.nullChar));
                    return;
            }
        }
    },
    CharacterReferenceInData {
        /* access modifiers changed from: 0000 */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            char[] consumeCharacterReference = tokeniser.consumeCharacterReference(null, false);
            if (consumeCharacterReference == null) {
                tokeniser.emit('&');
            } else {
                tokeniser.emit(consumeCharacterReference);
            }
            tokeniser.transition(Data);
        }
    },
    Rcdata {
        /* access modifiers changed from: 0000 */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            switch (characterReader.current()) {
                case 0:
                    tokeniser.error((TokeniserState) this);
                    characterReader.advance();
                    tokeniser.emit((char) TokeniserState.replacementChar);
                    return;
                case '&':
                    tokeniser.advanceTransition(CharacterReferenceInRcdata);
                    return;
                case '<':
                    tokeniser.advanceTransition(RcdataLessthanSign);
                    return;
                case 65535:
                    tokeniser.emit((Token) new EOF());
                    return;
                default:
                    tokeniser.emit(characterReader.consumeToAny('&', '<', TokeniserState.nullChar));
                    return;
            }
        }
    },
    CharacterReferenceInRcdata {
        /* access modifiers changed from: 0000 */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            char[] consumeCharacterReference = tokeniser.consumeCharacterReference(null, false);
            if (consumeCharacterReference == null) {
                tokeniser.emit('&');
            } else {
                tokeniser.emit(consumeCharacterReference);
            }
            tokeniser.transition(Rcdata);
        }
    },
    Rawtext {
        /* access modifiers changed from: 0000 */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            switch (characterReader.current()) {
                case 0:
                    tokeniser.error((TokeniserState) this);
                    characterReader.advance();
                    tokeniser.emit((char) TokeniserState.replacementChar);
                    return;
                case '<':
                    tokeniser.advanceTransition(RawtextLessthanSign);
                    return;
                case 65535:
                    tokeniser.emit((Token) new EOF());
                    return;
                default:
                    tokeniser.emit(characterReader.consumeToAny('<', TokeniserState.nullChar));
                    return;
            }
        }
    },
    ScriptData {
        /* access modifiers changed from: 0000 */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            switch (characterReader.current()) {
                case 0:
                    tokeniser.error((TokeniserState) this);
                    characterReader.advance();
                    tokeniser.emit((char) TokeniserState.replacementChar);
                    return;
                case '<':
                    tokeniser.advanceTransition(ScriptDataLessthanSign);
                    return;
                case 65535:
                    tokeniser.emit((Token) new EOF());
                    return;
                default:
                    tokeniser.emit(characterReader.consumeToAny('<', TokeniserState.nullChar));
                    return;
            }
        }
    },
    PLAINTEXT {
        /* access modifiers changed from: 0000 */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            switch (characterReader.current()) {
                case 0:
                    tokeniser.error((TokeniserState) this);
                    characterReader.advance();
                    tokeniser.emit((char) TokeniserState.replacementChar);
                    return;
                case 65535:
                    tokeniser.emit((Token) new EOF());
                    return;
                default:
                    tokeniser.emit(characterReader.consumeTo((char) TokeniserState.nullChar));
                    return;
            }
        }
    },
    TagOpen {
        /* access modifiers changed from: 0000 */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            switch (characterReader.current()) {
                case '!':
                    tokeniser.advanceTransition(MarkupDeclarationOpen);
                    return;
                case '/':
                    tokeniser.advanceTransition(EndTagOpen);
                    return;
                case '?':
                    tokeniser.advanceTransition(BogusComment);
                    return;
                default:
                    if (characterReader.matchesLetter()) {
                        tokeniser.createTagPending(true);
                        tokeniser.transition(TagName);
                        return;
                    }
                    tokeniser.error((TokeniserState) this);
                    tokeniser.emit('<');
                    tokeniser.transition(Data);
                    return;
            }
        }
    },
    EndTagOpen {
        /* access modifiers changed from: 0000 */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            if (characterReader.isEmpty()) {
                tokeniser.eofError(this);
                tokeniser.emit("</");
                tokeniser.transition(Data);
            } else if (characterReader.matchesLetter()) {
                tokeniser.createTagPending(false);
                tokeniser.transition(TagName);
            } else if (characterReader.matches('>')) {
                tokeniser.error((TokeniserState) this);
                tokeniser.advanceTransition(Data);
            } else {
                tokeniser.error((TokeniserState) this);
                tokeniser.advanceTransition(BogusComment);
            }
        }
    },
    TagName {
        /* access modifiers changed from: 0000 */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            tokeniser.tagPending.appendTagName(characterReader.consumeToAny(9, 10, 13, 12, ' ', '/', '>', TokeniserState.nullChar).toLowerCase());
            switch (characterReader.consume()) {
                case 0:
                    tokeniser.tagPending.appendTagName(TokeniserState.replacementStr);
                    return;
                case 9:
                case 10:
                case 12:
                case 13:
                case ' ':
                    tokeniser.transition(BeforeAttributeName);
                    return;
                case '/':
                    tokeniser.transition(SelfClosingStartTag);
                    return;
                case '>':
                    tokeniser.emitTagPending();
                    tokeniser.transition(Data);
                    return;
                case 65535:
                    tokeniser.eofError(this);
                    tokeniser.transition(Data);
                    return;
                default:
                    return;
            }
        }
    },
    RcdataLessthanSign {
        /* access modifiers changed from: 0000 */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            if (characterReader.matches('/')) {
                tokeniser.createTempBuffer();
                tokeniser.advanceTransition(RCDATAEndTagOpen);
            } else if (!characterReader.matchesLetter() || tokeniser.appropriateEndTagName() == null || characterReader.containsIgnoreCase("</" + tokeniser.appropriateEndTagName())) {
                tokeniser.emit("<");
                tokeniser.transition(Rcdata);
            } else {
                tokeniser.tagPending = new EndTag(tokeniser.appropriateEndTagName());
                tokeniser.emitTagPending();
                characterReader.unconsume();
                tokeniser.transition(Data);
            }
        }
    },
    RCDATAEndTagOpen {
        /* access modifiers changed from: 0000 */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            if (characterReader.matchesLetter()) {
                tokeniser.createTagPending(false);
                tokeniser.tagPending.appendTagName(Character.toLowerCase(characterReader.current()));
                tokeniser.dataBuffer.append(Character.toLowerCase(characterReader.current()));
                tokeniser.advanceTransition(RCDATAEndTagName);
                return;
            }
            tokeniser.emit("</");
            tokeniser.transition(Rcdata);
        }
    },
    RCDATAEndTagName {
        /* access modifiers changed from: 0000 */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            if (characterReader.matchesLetter()) {
                String consumeLetterSequence = characterReader.consumeLetterSequence();
                tokeniser.tagPending.appendTagName(consumeLetterSequence.toLowerCase());
                tokeniser.dataBuffer.append(consumeLetterSequence);
                return;
            }
            switch (characterReader.consume()) {
                case 9:
                case 10:
                case 12:
                case 13:
                case ' ':
                    if (tokeniser.isAppropriateEndTagToken()) {
                        tokeniser.transition(BeforeAttributeName);
                        return;
                    } else {
                        anythingElse(tokeniser, characterReader);
                        return;
                    }
                case '/':
                    if (tokeniser.isAppropriateEndTagToken()) {
                        tokeniser.transition(SelfClosingStartTag);
                        return;
                    } else {
                        anythingElse(tokeniser, characterReader);
                        return;
                    }
                case '>':
                    if (tokeniser.isAppropriateEndTagToken()) {
                        tokeniser.emitTagPending();
                        tokeniser.transition(Data);
                        return;
                    }
                    anythingElse(tokeniser, characterReader);
                    return;
                default:
                    anythingElse(tokeniser, characterReader);
                    return;
            }
        }

        private void anythingElse(Tokeniser tokeniser, CharacterReader characterReader) {
            tokeniser.emit("</" + tokeniser.dataBuffer.toString());
            characterReader.unconsume();
            tokeniser.transition(Rcdata);
        }
    },
    RawtextLessthanSign {
        /* access modifiers changed from: 0000 */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            if (characterReader.matches('/')) {
                tokeniser.createTempBuffer();
                tokeniser.advanceTransition(RawtextEndTagOpen);
                return;
            }
            tokeniser.emit('<');
            tokeniser.transition(Rawtext);
        }
    },
    RawtextEndTagOpen {
        /* access modifiers changed from: 0000 */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            if (characterReader.matchesLetter()) {
                tokeniser.createTagPending(false);
                tokeniser.transition(RawtextEndTagName);
                return;
            }
            tokeniser.emit("</");
            tokeniser.transition(Rawtext);
        }
    },
    RawtextEndTagName {
        /* access modifiers changed from: 0000 */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            TokeniserState.handleDataEndTag(tokeniser, characterReader, Rawtext);
        }
    },
    ScriptDataLessthanSign {
        /* access modifiers changed from: 0000 */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            switch (characterReader.consume()) {
                case '!':
                    tokeniser.emit("<!");
                    tokeniser.transition(ScriptDataEscapeStart);
                    return;
                case '/':
                    tokeniser.createTempBuffer();
                    tokeniser.transition(ScriptDataEndTagOpen);
                    return;
                default:
                    tokeniser.emit("<");
                    characterReader.unconsume();
                    tokeniser.transition(ScriptData);
                    return;
            }
        }
    },
    ScriptDataEndTagOpen {
        /* access modifiers changed from: 0000 */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            if (characterReader.matchesLetter()) {
                tokeniser.createTagPending(false);
                tokeniser.transition(ScriptDataEndTagName);
                return;
            }
            tokeniser.emit("</");
            tokeniser.transition(ScriptData);
        }
    },
    ScriptDataEndTagName {
        /* access modifiers changed from: 0000 */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            TokeniserState.handleDataEndTag(tokeniser, characterReader, ScriptData);
        }
    },
    ScriptDataEscapeStart {
        /* access modifiers changed from: 0000 */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            if (characterReader.matches('-')) {
                tokeniser.emit('-');
                tokeniser.advanceTransition(ScriptDataEscapeStartDash);
                return;
            }
            tokeniser.transition(ScriptData);
        }
    },
    ScriptDataEscapeStartDash {
        /* access modifiers changed from: 0000 */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            if (characterReader.matches('-')) {
                tokeniser.emit('-');
                tokeniser.advanceTransition(ScriptDataEscapedDashDash);
                return;
            }
            tokeniser.transition(ScriptData);
        }
    },
    ScriptDataEscaped {
        /* access modifiers changed from: 0000 */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            if (characterReader.isEmpty()) {
                tokeniser.eofError(this);
                tokeniser.transition(Data);
                return;
            }
            switch (characterReader.current()) {
                case 0:
                    tokeniser.error((TokeniserState) this);
                    characterReader.advance();
                    tokeniser.emit((char) TokeniserState.replacementChar);
                    return;
                case '-':
                    tokeniser.emit('-');
                    tokeniser.advanceTransition(ScriptDataEscapedDash);
                    return;
                case '<':
                    tokeniser.advanceTransition(ScriptDataEscapedLessthanSign);
                    return;
                default:
                    tokeniser.emit(characterReader.consumeToAny('-', '<', TokeniserState.nullChar));
                    return;
            }
        }
    },
    ScriptDataEscapedDash {
        /* access modifiers changed from: 0000 */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            if (characterReader.isEmpty()) {
                tokeniser.eofError(this);
                tokeniser.transition(Data);
                return;
            }
            char consume = characterReader.consume();
            switch (consume) {
                case 0:
                    tokeniser.error((TokeniserState) this);
                    tokeniser.emit((char) TokeniserState.replacementChar);
                    tokeniser.transition(ScriptDataEscaped);
                    return;
                case '-':
                    tokeniser.emit(consume);
                    tokeniser.transition(ScriptDataEscapedDashDash);
                    return;
                case '<':
                    tokeniser.transition(ScriptDataEscapedLessthanSign);
                    return;
                default:
                    tokeniser.emit(consume);
                    tokeniser.transition(ScriptDataEscaped);
                    return;
            }
        }
    },
    ScriptDataEscapedDashDash {
        /* access modifiers changed from: 0000 */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            if (characterReader.isEmpty()) {
                tokeniser.eofError(this);
                tokeniser.transition(Data);
                return;
            }
            char consume = characterReader.consume();
            switch (consume) {
                case 0:
                    tokeniser.error((TokeniserState) this);
                    tokeniser.emit((char) TokeniserState.replacementChar);
                    tokeniser.transition(ScriptDataEscaped);
                    return;
                case '-':
                    tokeniser.emit(consume);
                    return;
                case '<':
                    tokeniser.transition(ScriptDataEscapedLessthanSign);
                    return;
                case '>':
                    tokeniser.emit(consume);
                    tokeniser.transition(ScriptData);
                    return;
                default:
                    tokeniser.emit(consume);
                    tokeniser.transition(ScriptDataEscaped);
                    return;
            }
        }
    },
    ScriptDataEscapedLessthanSign {
        /* access modifiers changed from: 0000 */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            if (characterReader.matchesLetter()) {
                tokeniser.createTempBuffer();
                tokeniser.dataBuffer.append(Character.toLowerCase(characterReader.current()));
                tokeniser.emit("<" + characterReader.current());
                tokeniser.advanceTransition(ScriptDataDoubleEscapeStart);
            } else if (characterReader.matches('/')) {
                tokeniser.createTempBuffer();
                tokeniser.advanceTransition(ScriptDataEscapedEndTagOpen);
            } else {
                tokeniser.emit('<');
                tokeniser.transition(ScriptDataEscaped);
            }
        }
    },
    ScriptDataEscapedEndTagOpen {
        /* access modifiers changed from: 0000 */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            if (characterReader.matchesLetter()) {
                tokeniser.createTagPending(false);
                tokeniser.tagPending.appendTagName(Character.toLowerCase(characterReader.current()));
                tokeniser.dataBuffer.append(characterReader.current());
                tokeniser.advanceTransition(ScriptDataEscapedEndTagName);
                return;
            }
            tokeniser.emit("</");
            tokeniser.transition(ScriptDataEscaped);
        }
    },
    ScriptDataEscapedEndTagName {
        /* access modifiers changed from: 0000 */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            TokeniserState.handleDataEndTag(tokeniser, characterReader, ScriptDataEscaped);
        }
    },
    ScriptDataDoubleEscapeStart {
        /* access modifiers changed from: 0000 */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            TokeniserState.handleDataDoubleEscapeTag(tokeniser, characterReader, ScriptDataDoubleEscaped, ScriptDataEscaped);
        }
    },
    ScriptDataDoubleEscaped {
        /* access modifiers changed from: 0000 */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            char current = characterReader.current();
            switch (current) {
                case 0:
                    tokeniser.error((TokeniserState) this);
                    characterReader.advance();
                    tokeniser.emit((char) TokeniserState.replacementChar);
                    return;
                case '-':
                    tokeniser.emit(current);
                    tokeniser.advanceTransition(ScriptDataDoubleEscapedDash);
                    return;
                case '<':
                    tokeniser.emit(current);
                    tokeniser.advanceTransition(ScriptDataDoubleEscapedLessthanSign);
                    return;
                case 65535:
                    tokeniser.eofError(this);
                    tokeniser.transition(Data);
                    return;
                default:
                    tokeniser.emit(characterReader.consumeToAny('-', '<', TokeniserState.nullChar));
                    return;
            }
        }
    },
    ScriptDataDoubleEscapedDash {
        /* access modifiers changed from: 0000 */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            char consume = characterReader.consume();
            switch (consume) {
                case 0:
                    tokeniser.error((TokeniserState) this);
                    tokeniser.emit((char) TokeniserState.replacementChar);
                    tokeniser.transition(ScriptDataDoubleEscaped);
                    return;
                case '-':
                    tokeniser.emit(consume);
                    tokeniser.transition(ScriptDataDoubleEscapedDashDash);
                    return;
                case '<':
                    tokeniser.emit(consume);
                    tokeniser.transition(ScriptDataDoubleEscapedLessthanSign);
                    return;
                case 65535:
                    tokeniser.eofError(this);
                    tokeniser.transition(Data);
                    return;
                default:
                    tokeniser.emit(consume);
                    tokeniser.transition(ScriptDataDoubleEscaped);
                    return;
            }
        }
    },
    ScriptDataDoubleEscapedDashDash {
        /* access modifiers changed from: 0000 */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            char consume = characterReader.consume();
            switch (consume) {
                case 0:
                    tokeniser.error((TokeniserState) this);
                    tokeniser.emit((char) TokeniserState.replacementChar);
                    tokeniser.transition(ScriptDataDoubleEscaped);
                    return;
                case '-':
                    tokeniser.emit(consume);
                    return;
                case '<':
                    tokeniser.emit(consume);
                    tokeniser.transition(ScriptDataDoubleEscapedLessthanSign);
                    return;
                case '>':
                    tokeniser.emit(consume);
                    tokeniser.transition(ScriptData);
                    return;
                case 65535:
                    tokeniser.eofError(this);
                    tokeniser.transition(Data);
                    return;
                default:
                    tokeniser.emit(consume);
                    tokeniser.transition(ScriptDataDoubleEscaped);
                    return;
            }
        }
    },
    ScriptDataDoubleEscapedLessthanSign {
        /* access modifiers changed from: 0000 */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            if (characterReader.matches('/')) {
                tokeniser.emit('/');
                tokeniser.createTempBuffer();
                tokeniser.advanceTransition(ScriptDataDoubleEscapeEnd);
                return;
            }
            tokeniser.transition(ScriptDataDoubleEscaped);
        }
    },
    ScriptDataDoubleEscapeEnd {
        /* access modifiers changed from: 0000 */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            TokeniserState.handleDataDoubleEscapeTag(tokeniser, characterReader, ScriptDataEscaped, ScriptDataDoubleEscaped);
        }
    },
    BeforeAttributeName {
        /* access modifiers changed from: 0000 */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            char consume = characterReader.consume();
            switch (consume) {
                case 0:
                    tokeniser.error((TokeniserState) this);
                    tokeniser.tagPending.newAttribute();
                    characterReader.unconsume();
                    tokeniser.transition(AttributeName);
                    return;
                case 9:
                case 10:
                case 12:
                case 13:
                case ' ':
                    return;
                case '\"':
                case '\'':
                case '<':
                case '=':
                    tokeniser.error((TokeniserState) this);
                    tokeniser.tagPending.newAttribute();
                    tokeniser.tagPending.appendAttributeName(consume);
                    tokeniser.transition(AttributeName);
                    return;
                case '/':
                    tokeniser.transition(SelfClosingStartTag);
                    return;
                case '>':
                    tokeniser.emitTagPending();
                    tokeniser.transition(Data);
                    return;
                case 65535:
                    tokeniser.eofError(this);
                    tokeniser.transition(Data);
                    return;
                default:
                    tokeniser.tagPending.newAttribute();
                    characterReader.unconsume();
                    tokeniser.transition(AttributeName);
                    return;
            }
        }
    },
    AttributeName {
        /* access modifiers changed from: 0000 */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            tokeniser.tagPending.appendAttributeName(characterReader.consumeToAny(9, 10, 13, 12, ' ', '/', '=', '>', TokeniserState.nullChar, '\"', '\'', '<').toLowerCase());
            char consume = characterReader.consume();
            switch (consume) {
                case 0:
                    tokeniser.error((TokeniserState) this);
                    tokeniser.tagPending.appendAttributeName((char) TokeniserState.replacementChar);
                    return;
                case 9:
                case 10:
                case 12:
                case 13:
                case ' ':
                    tokeniser.transition(AfterAttributeName);
                    return;
                case '\"':
                case '\'':
                case '<':
                    tokeniser.error((TokeniserState) this);
                    tokeniser.tagPending.appendAttributeName(consume);
                    return;
                case '/':
                    tokeniser.transition(SelfClosingStartTag);
                    return;
                case '=':
                    tokeniser.transition(BeforeAttributeValue);
                    return;
                case '>':
                    tokeniser.emitTagPending();
                    tokeniser.transition(Data);
                    return;
                case 65535:
                    tokeniser.eofError(this);
                    tokeniser.transition(Data);
                    return;
                default:
                    return;
            }
        }
    },
    AfterAttributeName {
        /* access modifiers changed from: 0000 */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            char consume = characterReader.consume();
            switch (consume) {
                case 0:
                    tokeniser.error((TokeniserState) this);
                    tokeniser.tagPending.appendAttributeName((char) TokeniserState.replacementChar);
                    tokeniser.transition(AttributeName);
                    return;
                case 9:
                case 10:
                case 12:
                case 13:
                case ' ':
                    return;
                case '\"':
                case '\'':
                case '<':
                    tokeniser.error((TokeniserState) this);
                    tokeniser.tagPending.newAttribute();
                    tokeniser.tagPending.appendAttributeName(consume);
                    tokeniser.transition(AttributeName);
                    return;
                case '/':
                    tokeniser.transition(SelfClosingStartTag);
                    return;
                case '=':
                    tokeniser.transition(BeforeAttributeValue);
                    return;
                case '>':
                    tokeniser.emitTagPending();
                    tokeniser.transition(Data);
                    return;
                case 65535:
                    tokeniser.eofError(this);
                    tokeniser.transition(Data);
                    return;
                default:
                    tokeniser.tagPending.newAttribute();
                    characterReader.unconsume();
                    tokeniser.transition(AttributeName);
                    return;
            }
        }
    },
    BeforeAttributeValue {
        /* access modifiers changed from: 0000 */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            char consume = characterReader.consume();
            switch (consume) {
                case 0:
                    tokeniser.error((TokeniserState) this);
                    tokeniser.tagPending.appendAttributeValue((char) TokeniserState.replacementChar);
                    tokeniser.transition(AttributeValue_unquoted);
                    return;
                case 9:
                case 10:
                case 12:
                case 13:
                case ' ':
                    return;
                case '\"':
                    tokeniser.transition(AttributeValue_doubleQuoted);
                    return;
                case '&':
                    characterReader.unconsume();
                    tokeniser.transition(AttributeValue_unquoted);
                    return;
                case '\'':
                    tokeniser.transition(AttributeValue_singleQuoted);
                    return;
                case '<':
                case '=':
                case '`':
                    tokeniser.error((TokeniserState) this);
                    tokeniser.tagPending.appendAttributeValue(consume);
                    tokeniser.transition(AttributeValue_unquoted);
                    return;
                case '>':
                    tokeniser.error((TokeniserState) this);
                    tokeniser.emitTagPending();
                    tokeniser.transition(Data);
                    return;
                case 65535:
                    tokeniser.eofError(this);
                    tokeniser.transition(Data);
                    return;
                default:
                    characterReader.unconsume();
                    tokeniser.transition(AttributeValue_unquoted);
                    return;
            }
        }
    },
    AttributeValue_doubleQuoted {
        /* access modifiers changed from: 0000 */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            String consumeToAny = characterReader.consumeToAny('\"', '&', TokeniserState.nullChar);
            if (consumeToAny.length() > 0) {
                tokeniser.tagPending.appendAttributeValue(consumeToAny);
            }
            switch (characterReader.consume()) {
                case 0:
                    tokeniser.error((TokeniserState) this);
                    tokeniser.tagPending.appendAttributeValue((char) TokeniserState.replacementChar);
                    return;
                case '\"':
                    tokeniser.transition(AfterAttributeValue_quoted);
                    return;
                case '&':
                    char[] consumeCharacterReference = tokeniser.consumeCharacterReference(Character.valueOf('\"'), true);
                    if (consumeCharacterReference != null) {
                        tokeniser.tagPending.appendAttributeValue(consumeCharacterReference);
                        return;
                    } else {
                        tokeniser.tagPending.appendAttributeValue('&');
                        return;
                    }
                case 65535:
                    tokeniser.eofError(this);
                    tokeniser.transition(Data);
                    return;
                default:
                    return;
            }
        }
    },
    AttributeValue_singleQuoted {
        /* access modifiers changed from: 0000 */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            String consumeToAny = characterReader.consumeToAny('\'', '&', TokeniserState.nullChar);
            if (consumeToAny.length() > 0) {
                tokeniser.tagPending.appendAttributeValue(consumeToAny);
            }
            switch (characterReader.consume()) {
                case 0:
                    tokeniser.error((TokeniserState) this);
                    tokeniser.tagPending.appendAttributeValue((char) TokeniserState.replacementChar);
                    return;
                case '&':
                    char[] consumeCharacterReference = tokeniser.consumeCharacterReference(Character.valueOf('\''), true);
                    if (consumeCharacterReference != null) {
                        tokeniser.tagPending.appendAttributeValue(consumeCharacterReference);
                        return;
                    } else {
                        tokeniser.tagPending.appendAttributeValue('&');
                        return;
                    }
                case '\'':
                    tokeniser.transition(AfterAttributeValue_quoted);
                    return;
                case 65535:
                    tokeniser.eofError(this);
                    tokeniser.transition(Data);
                    return;
                default:
                    return;
            }
        }
    },
    AttributeValue_unquoted {
        /* access modifiers changed from: 0000 */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            String consumeToAny = characterReader.consumeToAny(9, 10, 13, 12, ' ', '&', '>', TokeniserState.nullChar, '\"', '\'', '<', '=', '`');
            if (consumeToAny.length() > 0) {
                tokeniser.tagPending.appendAttributeValue(consumeToAny);
            }
            char consume = characterReader.consume();
            switch (consume) {
                case 0:
                    tokeniser.error((TokeniserState) this);
                    tokeniser.tagPending.appendAttributeValue((char) TokeniserState.replacementChar);
                    return;
                case 9:
                case 10:
                case 12:
                case 13:
                case ' ':
                    tokeniser.transition(BeforeAttributeName);
                    return;
                case '\"':
                case '\'':
                case '<':
                case '=':
                case '`':
                    tokeniser.error((TokeniserState) this);
                    tokeniser.tagPending.appendAttributeValue(consume);
                    return;
                case '&':
                    char[] consumeCharacterReference = tokeniser.consumeCharacterReference(Character.valueOf('>'), true);
                    if (consumeCharacterReference != null) {
                        tokeniser.tagPending.appendAttributeValue(consumeCharacterReference);
                        return;
                    } else {
                        tokeniser.tagPending.appendAttributeValue('&');
                        return;
                    }
                case '>':
                    tokeniser.emitTagPending();
                    tokeniser.transition(Data);
                    return;
                case 65535:
                    tokeniser.eofError(this);
                    tokeniser.transition(Data);
                    return;
                default:
                    return;
            }
        }
    },
    AfterAttributeValue_quoted {
        /* access modifiers changed from: 0000 */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            switch (characterReader.consume()) {
                case 9:
                case 10:
                case 12:
                case 13:
                case ' ':
                    tokeniser.transition(BeforeAttributeName);
                    return;
                case '/':
                    tokeniser.transition(SelfClosingStartTag);
                    return;
                case '>':
                    tokeniser.emitTagPending();
                    tokeniser.transition(Data);
                    return;
                case 65535:
                    tokeniser.eofError(this);
                    tokeniser.transition(Data);
                    return;
                default:
                    tokeniser.error((TokeniserState) this);
                    characterReader.unconsume();
                    tokeniser.transition(BeforeAttributeName);
                    return;
            }
        }
    },
    SelfClosingStartTag {
        /* access modifiers changed from: 0000 */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            switch (characterReader.consume()) {
                case '>':
                    tokeniser.tagPending.selfClosing = true;
                    tokeniser.emitTagPending();
                    tokeniser.transition(Data);
                    return;
                case 65535:
                    tokeniser.eofError(this);
                    tokeniser.transition(Data);
                    return;
                default:
                    tokeniser.error((TokeniserState) this);
                    tokeniser.transition(BeforeAttributeName);
                    return;
            }
        }
    },
    BogusComment {
        /* access modifiers changed from: 0000 */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            characterReader.unconsume();
            Comment comment = new Comment();
            comment.bogus = true;
            comment.data.append(characterReader.consumeTo('>'));
            tokeniser.emit((Token) comment);
            tokeniser.advanceTransition(Data);
        }
    },
    MarkupDeclarationOpen {
        /* access modifiers changed from: 0000 */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            if (characterReader.matchConsume("--")) {
                tokeniser.createCommentPending();
                tokeniser.transition(CommentStart);
            } else if (characterReader.matchConsumeIgnoreCase("DOCTYPE")) {
                tokeniser.transition(Doctype);
            } else if (characterReader.matchConsume("[CDATA[")) {
                tokeniser.transition(CdataSection);
            } else {
                tokeniser.error((TokeniserState) this);
                tokeniser.advanceTransition(BogusComment);
            }
        }
    },
    CommentStart {
        /* access modifiers changed from: 0000 */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            char consume = characterReader.consume();
            switch (consume) {
                case 0:
                    tokeniser.error((TokeniserState) this);
                    tokeniser.commentPending.data.append(TokeniserState.replacementChar);
                    tokeniser.transition(Comment);
                    return;
                case '-':
                    tokeniser.transition(CommentStartDash);
                    return;
                case '>':
                    tokeniser.error((TokeniserState) this);
                    tokeniser.emitCommentPending();
                    tokeniser.transition(Data);
                    return;
                case 65535:
                    tokeniser.eofError(this);
                    tokeniser.emitCommentPending();
                    tokeniser.transition(Data);
                    return;
                default:
                    tokeniser.commentPending.data.append(consume);
                    tokeniser.transition(Comment);
                    return;
            }
        }
    },
    CommentStartDash {
        /* access modifiers changed from: 0000 */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            char consume = characterReader.consume();
            switch (consume) {
                case 0:
                    tokeniser.error((TokeniserState) this);
                    tokeniser.commentPending.data.append(TokeniserState.replacementChar);
                    tokeniser.transition(Comment);
                    return;
                case '-':
                    tokeniser.transition(CommentStartDash);
                    return;
                case '>':
                    tokeniser.error((TokeniserState) this);
                    tokeniser.emitCommentPending();
                    tokeniser.transition(Data);
                    return;
                case 65535:
                    tokeniser.eofError(this);
                    tokeniser.emitCommentPending();
                    tokeniser.transition(Data);
                    return;
                default:
                    tokeniser.commentPending.data.append(consume);
                    tokeniser.transition(Comment);
                    return;
            }
        }
    },
    Comment {
        /* access modifiers changed from: 0000 */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            switch (characterReader.current()) {
                case 0:
                    tokeniser.error((TokeniserState) this);
                    characterReader.advance();
                    tokeniser.commentPending.data.append(TokeniserState.replacementChar);
                    return;
                case '-':
                    tokeniser.advanceTransition(CommentEndDash);
                    return;
                case 65535:
                    tokeniser.eofError(this);
                    tokeniser.emitCommentPending();
                    tokeniser.transition(Data);
                    return;
                default:
                    tokeniser.commentPending.data.append(characterReader.consumeToAny('-', TokeniserState.nullChar));
                    return;
            }
        }
    },
    CommentEndDash {
        /* access modifiers changed from: 0000 */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            char consume = characterReader.consume();
            switch (consume) {
                case 0:
                    tokeniser.error((TokeniserState) this);
                    tokeniser.commentPending.data.append('-').append(TokeniserState.replacementChar);
                    tokeniser.transition(Comment);
                    return;
                case '-':
                    tokeniser.transition(CommentEnd);
                    return;
                case 65535:
                    tokeniser.eofError(this);
                    tokeniser.emitCommentPending();
                    tokeniser.transition(Data);
                    return;
                default:
                    tokeniser.commentPending.data.append('-').append(consume);
                    tokeniser.transition(Comment);
                    return;
            }
        }
    },
    CommentEnd {
        /* access modifiers changed from: 0000 */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            char consume = characterReader.consume();
            switch (consume) {
                case 0:
                    tokeniser.error((TokeniserState) this);
                    tokeniser.commentPending.data.append("--").append(TokeniserState.replacementChar);
                    tokeniser.transition(Comment);
                    return;
                case '!':
                    tokeniser.error((TokeniserState) this);
                    tokeniser.transition(CommentEndBang);
                    return;
                case '-':
                    tokeniser.error((TokeniserState) this);
                    tokeniser.commentPending.data.append('-');
                    return;
                case '>':
                    tokeniser.emitCommentPending();
                    tokeniser.transition(Data);
                    return;
                case 65535:
                    tokeniser.eofError(this);
                    tokeniser.emitCommentPending();
                    tokeniser.transition(Data);
                    return;
                default:
                    tokeniser.error((TokeniserState) this);
                    tokeniser.commentPending.data.append("--").append(consume);
                    tokeniser.transition(Comment);
                    return;
            }
        }
    },
    CommentEndBang {
        /* access modifiers changed from: 0000 */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            char consume = characterReader.consume();
            switch (consume) {
                case 0:
                    tokeniser.error((TokeniserState) this);
                    tokeniser.commentPending.data.append("--!").append(TokeniserState.replacementChar);
                    tokeniser.transition(Comment);
                    return;
                case '-':
                    tokeniser.commentPending.data.append("--!");
                    tokeniser.transition(CommentEndDash);
                    return;
                case '>':
                    tokeniser.emitCommentPending();
                    tokeniser.transition(Data);
                    return;
                case 65535:
                    tokeniser.eofError(this);
                    tokeniser.emitCommentPending();
                    tokeniser.transition(Data);
                    return;
                default:
                    tokeniser.commentPending.data.append("--!").append(consume);
                    tokeniser.transition(Comment);
                    return;
            }
        }
    },
    Doctype {
        /* access modifiers changed from: 0000 */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            switch (characterReader.consume()) {
                case 9:
                case 10:
                case 12:
                case 13:
                case ' ':
                    tokeniser.transition(BeforeDoctypeName);
                    return;
                case '>':
                    break;
                case 65535:
                    tokeniser.eofError(this);
                    break;
                default:
                    tokeniser.error((TokeniserState) this);
                    tokeniser.transition(BeforeDoctypeName);
                    return;
            }
            tokeniser.error((TokeniserState) this);
            tokeniser.createDoctypePending();
            tokeniser.doctypePending.forceQuirks = true;
            tokeniser.emitDoctypePending();
            tokeniser.transition(Data);
        }
    },
    BeforeDoctypeName {
        /* access modifiers changed from: 0000 */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            if (characterReader.matchesLetter()) {
                tokeniser.createDoctypePending();
                tokeniser.transition(DoctypeName);
                return;
            }
            char consume = characterReader.consume();
            switch (consume) {
                case 0:
                    tokeniser.error((TokeniserState) this);
                    tokeniser.createDoctypePending();
                    tokeniser.doctypePending.name.append(TokeniserState.replacementChar);
                    tokeniser.transition(DoctypeName);
                    return;
                case 9:
                case 10:
                case 12:
                case 13:
                case ' ':
                    return;
                case 65535:
                    tokeniser.eofError(this);
                    tokeniser.createDoctypePending();
                    tokeniser.doctypePending.forceQuirks = true;
                    tokeniser.emitDoctypePending();
                    tokeniser.transition(Data);
                    return;
                default:
                    tokeniser.createDoctypePending();
                    tokeniser.doctypePending.name.append(consume);
                    tokeniser.transition(DoctypeName);
                    return;
            }
        }
    },
    DoctypeName {
        /* access modifiers changed from: 0000 */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            if (characterReader.matchesLetter()) {
                tokeniser.doctypePending.name.append(characterReader.consumeLetterSequence().toLowerCase());
                return;
            }
            char consume = characterReader.consume();
            switch (consume) {
                case 0:
                    tokeniser.error((TokeniserState) this);
                    tokeniser.doctypePending.name.append(TokeniserState.replacementChar);
                    return;
                case 9:
                case 10:
                case 12:
                case 13:
                case ' ':
                    tokeniser.transition(AfterDoctypeName);
                    return;
                case '>':
                    tokeniser.emitDoctypePending();
                    tokeniser.transition(Data);
                    return;
                case 65535:
                    tokeniser.eofError(this);
                    tokeniser.doctypePending.forceQuirks = true;
                    tokeniser.emitDoctypePending();
                    tokeniser.transition(Data);
                    return;
                default:
                    tokeniser.doctypePending.name.append(consume);
                    return;
            }
        }
    },
    AfterDoctypeName {
        /* access modifiers changed from: 0000 */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            if (characterReader.isEmpty()) {
                tokeniser.eofError(this);
                tokeniser.doctypePending.forceQuirks = true;
                tokeniser.emitDoctypePending();
                tokeniser.transition(Data);
            } else if (characterReader.matchesAny(9, 10, 13, 12, ' ')) {
                characterReader.advance();
            } else if (characterReader.matches('>')) {
                tokeniser.emitDoctypePending();
                tokeniser.advanceTransition(Data);
            } else if (characterReader.matchConsumeIgnoreCase("PUBLIC")) {
                tokeniser.transition(AfterDoctypePublicKeyword);
            } else if (characterReader.matchConsumeIgnoreCase("SYSTEM")) {
                tokeniser.transition(AfterDoctypeSystemKeyword);
            } else {
                tokeniser.error((TokeniserState) this);
                tokeniser.doctypePending.forceQuirks = true;
                tokeniser.advanceTransition(BogusDoctype);
            }
        }
    },
    AfterDoctypePublicKeyword {
        /* access modifiers changed from: 0000 */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            switch (characterReader.consume()) {
                case 9:
                case 10:
                case 12:
                case 13:
                case ' ':
                    tokeniser.transition(BeforeDoctypePublicIdentifier);
                    return;
                case '\"':
                    tokeniser.error((TokeniserState) this);
                    tokeniser.transition(DoctypePublicIdentifier_doubleQuoted);
                    return;
                case '\'':
                    tokeniser.error((TokeniserState) this);
                    tokeniser.transition(DoctypePublicIdentifier_singleQuoted);
                    return;
                case '>':
                    tokeniser.error((TokeniserState) this);
                    tokeniser.doctypePending.forceQuirks = true;
                    tokeniser.emitDoctypePending();
                    tokeniser.transition(Data);
                    return;
                case 65535:
                    tokeniser.eofError(this);
                    tokeniser.doctypePending.forceQuirks = true;
                    tokeniser.emitDoctypePending();
                    tokeniser.transition(Data);
                    return;
                default:
                    tokeniser.error((TokeniserState) this);
                    tokeniser.doctypePending.forceQuirks = true;
                    tokeniser.transition(BogusDoctype);
                    return;
            }
        }
    },
    BeforeDoctypePublicIdentifier {
        /* access modifiers changed from: 0000 */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            switch (characterReader.consume()) {
                case 9:
                case 10:
                case 12:
                case 13:
                case ' ':
                    return;
                case '\"':
                    tokeniser.transition(DoctypePublicIdentifier_doubleQuoted);
                    return;
                case '\'':
                    tokeniser.transition(DoctypePublicIdentifier_singleQuoted);
                    return;
                case '>':
                    tokeniser.error((TokeniserState) this);
                    tokeniser.doctypePending.forceQuirks = true;
                    tokeniser.emitDoctypePending();
                    tokeniser.transition(Data);
                    return;
                case 65535:
                    tokeniser.eofError(this);
                    tokeniser.doctypePending.forceQuirks = true;
                    tokeniser.emitDoctypePending();
                    tokeniser.transition(Data);
                    return;
                default:
                    tokeniser.error((TokeniserState) this);
                    tokeniser.doctypePending.forceQuirks = true;
                    tokeniser.transition(BogusDoctype);
                    return;
            }
        }
    },
    DoctypePublicIdentifier_doubleQuoted {
        /* access modifiers changed from: 0000 */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            char consume = characterReader.consume();
            switch (consume) {
                case 0:
                    tokeniser.error((TokeniserState) this);
                    tokeniser.doctypePending.publicIdentifier.append(TokeniserState.replacementChar);
                    return;
                case '\"':
                    tokeniser.transition(AfterDoctypePublicIdentifier);
                    return;
                case '>':
                    tokeniser.error((TokeniserState) this);
                    tokeniser.doctypePending.forceQuirks = true;
                    tokeniser.emitDoctypePending();
                    tokeniser.transition(Data);
                    return;
                case 65535:
                    tokeniser.eofError(this);
                    tokeniser.doctypePending.forceQuirks = true;
                    tokeniser.emitDoctypePending();
                    tokeniser.transition(Data);
                    return;
                default:
                    tokeniser.doctypePending.publicIdentifier.append(consume);
                    return;
            }
        }
    },
    DoctypePublicIdentifier_singleQuoted {
        /* access modifiers changed from: 0000 */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            char consume = characterReader.consume();
            switch (consume) {
                case 0:
                    tokeniser.error((TokeniserState) this);
                    tokeniser.doctypePending.publicIdentifier.append(TokeniserState.replacementChar);
                    return;
                case '\'':
                    tokeniser.transition(AfterDoctypePublicIdentifier);
                    return;
                case '>':
                    tokeniser.error((TokeniserState) this);
                    tokeniser.doctypePending.forceQuirks = true;
                    tokeniser.emitDoctypePending();
                    tokeniser.transition(Data);
                    return;
                case 65535:
                    tokeniser.eofError(this);
                    tokeniser.doctypePending.forceQuirks = true;
                    tokeniser.emitDoctypePending();
                    tokeniser.transition(Data);
                    return;
                default:
                    tokeniser.doctypePending.publicIdentifier.append(consume);
                    return;
            }
        }
    },
    AfterDoctypePublicIdentifier {
        /* access modifiers changed from: 0000 */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            switch (characterReader.consume()) {
                case 9:
                case 10:
                case 12:
                case 13:
                case ' ':
                    tokeniser.transition(BetweenDoctypePublicAndSystemIdentifiers);
                    return;
                case '\"':
                    tokeniser.error((TokeniserState) this);
                    tokeniser.transition(DoctypeSystemIdentifier_doubleQuoted);
                    return;
                case '\'':
                    tokeniser.error((TokeniserState) this);
                    tokeniser.transition(DoctypeSystemIdentifier_singleQuoted);
                    return;
                case '>':
                    tokeniser.emitDoctypePending();
                    tokeniser.transition(Data);
                    return;
                case 65535:
                    tokeniser.eofError(this);
                    tokeniser.doctypePending.forceQuirks = true;
                    tokeniser.emitDoctypePending();
                    tokeniser.transition(Data);
                    return;
                default:
                    tokeniser.error((TokeniserState) this);
                    tokeniser.doctypePending.forceQuirks = true;
                    tokeniser.transition(BogusDoctype);
                    return;
            }
        }
    },
    BetweenDoctypePublicAndSystemIdentifiers {
        /* access modifiers changed from: 0000 */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            switch (characterReader.consume()) {
                case 9:
                case 10:
                case 12:
                case 13:
                case ' ':
                    return;
                case '\"':
                    tokeniser.error((TokeniserState) this);
                    tokeniser.transition(DoctypeSystemIdentifier_doubleQuoted);
                    return;
                case '\'':
                    tokeniser.error((TokeniserState) this);
                    tokeniser.transition(DoctypeSystemIdentifier_singleQuoted);
                    return;
                case '>':
                    tokeniser.emitDoctypePending();
                    tokeniser.transition(Data);
                    return;
                case 65535:
                    tokeniser.eofError(this);
                    tokeniser.doctypePending.forceQuirks = true;
                    tokeniser.emitDoctypePending();
                    tokeniser.transition(Data);
                    return;
                default:
                    tokeniser.error((TokeniserState) this);
                    tokeniser.doctypePending.forceQuirks = true;
                    tokeniser.transition(BogusDoctype);
                    return;
            }
        }
    },
    AfterDoctypeSystemKeyword {
        /* access modifiers changed from: 0000 */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            switch (characterReader.consume()) {
                case 9:
                case 10:
                case 12:
                case 13:
                case ' ':
                    tokeniser.transition(BeforeDoctypeSystemIdentifier);
                    return;
                case '\"':
                    tokeniser.error((TokeniserState) this);
                    tokeniser.transition(DoctypeSystemIdentifier_doubleQuoted);
                    return;
                case '\'':
                    tokeniser.error((TokeniserState) this);
                    tokeniser.transition(DoctypeSystemIdentifier_singleQuoted);
                    return;
                case '>':
                    tokeniser.error((TokeniserState) this);
                    tokeniser.doctypePending.forceQuirks = true;
                    tokeniser.emitDoctypePending();
                    tokeniser.transition(Data);
                    return;
                case 65535:
                    tokeniser.eofError(this);
                    tokeniser.doctypePending.forceQuirks = true;
                    tokeniser.emitDoctypePending();
                    tokeniser.transition(Data);
                    return;
                default:
                    tokeniser.error((TokeniserState) this);
                    tokeniser.doctypePending.forceQuirks = true;
                    tokeniser.emitDoctypePending();
                    return;
            }
        }
    },
    BeforeDoctypeSystemIdentifier {
        /* access modifiers changed from: 0000 */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            switch (characterReader.consume()) {
                case 9:
                case 10:
                case 12:
                case 13:
                case ' ':
                    return;
                case '\"':
                    tokeniser.transition(DoctypeSystemIdentifier_doubleQuoted);
                    return;
                case '\'':
                    tokeniser.transition(DoctypeSystemIdentifier_singleQuoted);
                    return;
                case '>':
                    tokeniser.error((TokeniserState) this);
                    tokeniser.doctypePending.forceQuirks = true;
                    tokeniser.emitDoctypePending();
                    tokeniser.transition(Data);
                    return;
                case 65535:
                    tokeniser.eofError(this);
                    tokeniser.doctypePending.forceQuirks = true;
                    tokeniser.emitDoctypePending();
                    tokeniser.transition(Data);
                    return;
                default:
                    tokeniser.error((TokeniserState) this);
                    tokeniser.doctypePending.forceQuirks = true;
                    tokeniser.transition(BogusDoctype);
                    return;
            }
        }
    },
    DoctypeSystemIdentifier_doubleQuoted {
        /* access modifiers changed from: 0000 */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            char consume = characterReader.consume();
            switch (consume) {
                case 0:
                    tokeniser.error((TokeniserState) this);
                    tokeniser.doctypePending.systemIdentifier.append(TokeniserState.replacementChar);
                    return;
                case '\"':
                    tokeniser.transition(AfterDoctypeSystemIdentifier);
                    return;
                case '>':
                    tokeniser.error((TokeniserState) this);
                    tokeniser.doctypePending.forceQuirks = true;
                    tokeniser.emitDoctypePending();
                    tokeniser.transition(Data);
                    return;
                case 65535:
                    tokeniser.eofError(this);
                    tokeniser.doctypePending.forceQuirks = true;
                    tokeniser.emitDoctypePending();
                    tokeniser.transition(Data);
                    return;
                default:
                    tokeniser.doctypePending.systemIdentifier.append(consume);
                    return;
            }
        }
    },
    DoctypeSystemIdentifier_singleQuoted {
        /* access modifiers changed from: 0000 */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            char consume = characterReader.consume();
            switch (consume) {
                case 0:
                    tokeniser.error((TokeniserState) this);
                    tokeniser.doctypePending.systemIdentifier.append(TokeniserState.replacementChar);
                    return;
                case '\'':
                    tokeniser.transition(AfterDoctypeSystemIdentifier);
                    return;
                case '>':
                    tokeniser.error((TokeniserState) this);
                    tokeniser.doctypePending.forceQuirks = true;
                    tokeniser.emitDoctypePending();
                    tokeniser.transition(Data);
                    return;
                case 65535:
                    tokeniser.eofError(this);
                    tokeniser.doctypePending.forceQuirks = true;
                    tokeniser.emitDoctypePending();
                    tokeniser.transition(Data);
                    return;
                default:
                    tokeniser.doctypePending.systemIdentifier.append(consume);
                    return;
            }
        }
    },
    AfterDoctypeSystemIdentifier {
        /* access modifiers changed from: 0000 */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            switch (characterReader.consume()) {
                case 9:
                case 10:
                case 12:
                case 13:
                case ' ':
                    return;
                case '>':
                    tokeniser.emitDoctypePending();
                    tokeniser.transition(Data);
                    return;
                case 65535:
                    tokeniser.eofError(this);
                    tokeniser.doctypePending.forceQuirks = true;
                    tokeniser.emitDoctypePending();
                    tokeniser.transition(Data);
                    return;
                default:
                    tokeniser.error((TokeniserState) this);
                    tokeniser.transition(BogusDoctype);
                    return;
            }
        }
    },
    BogusDoctype {
        /* access modifiers changed from: 0000 */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            switch (characterReader.consume()) {
                case '>':
                    tokeniser.emitDoctypePending();
                    tokeniser.transition(Data);
                    return;
                case 65535:
                    tokeniser.emitDoctypePending();
                    tokeniser.transition(Data);
                    return;
                default:
                    return;
            }
        }
    },
    CdataSection {
        /* access modifiers changed from: 0000 */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            tokeniser.emit(characterReader.consumeTo("]]>"));
            characterReader.matchConsume("]]>");
            tokeniser.transition(Data);
        }
    };
    
    private static final char eof = '￿';
    private static final char nullChar = '\u0000';
    private static final char replacementChar = '�';
    /* access modifiers changed from: private */
    public static final String replacementStr = null;

    /* access modifiers changed from: 0000 */
    public abstract void read(Tokeniser tokeniser, CharacterReader characterReader);

    static {
        replacementStr = String.valueOf(replacementChar);
    }

    /* access modifiers changed from: private */
    public static final void handleDataEndTag(Tokeniser tokeniser, CharacterReader characterReader, TokeniserState tokeniserState) {
        if (characterReader.matchesLetter()) {
            String consumeLetterSequence = characterReader.consumeLetterSequence();
            tokeniser.tagPending.appendTagName(consumeLetterSequence.toLowerCase());
            tokeniser.dataBuffer.append(consumeLetterSequence);
            return;
        }
        boolean z = false;
        if (tokeniser.isAppropriateEndTagToken() && !characterReader.isEmpty()) {
            char consume = characterReader.consume();
            switch (consume) {
                case 9:
                case 10:
                case 12:
                case 13:
                case ' ':
                    tokeniser.transition(BeforeAttributeName);
                    break;
                case '/':
                    tokeniser.transition(SelfClosingStartTag);
                    break;
                case '>':
                    tokeniser.emitTagPending();
                    tokeniser.transition(Data);
                    break;
                default:
                    tokeniser.dataBuffer.append(consume);
                    z = true;
                    break;
            }
        } else {
            z = true;
        }
        if (z) {
            tokeniser.emit("</" + tokeniser.dataBuffer.toString());
            tokeniser.transition(tokeniserState);
        }
    }

    /* access modifiers changed from: private */
    public static final void handleDataDoubleEscapeTag(Tokeniser tokeniser, CharacterReader characterReader, TokeniserState tokeniserState, TokeniserState tokeniserState2) {
        if (characterReader.matchesLetter()) {
            String consumeLetterSequence = characterReader.consumeLetterSequence();
            tokeniser.dataBuffer.append(consumeLetterSequence.toLowerCase());
            tokeniser.emit(consumeLetterSequence);
            return;
        }
        char consume = characterReader.consume();
        switch (consume) {
            case 9:
            case 10:
            case 12:
            case 13:
            case ' ':
            case '/':
            case '>':
                if (tokeniser.dataBuffer.toString().equals("script")) {
                    tokeniser.transition(tokeniserState);
                } else {
                    tokeniser.transition(tokeniserState2);
                }
                tokeniser.emit(consume);
                return;
            default:
                characterReader.unconsume();
                tokeniser.transition(tokeniserState2);
                return;
        }
    }
}

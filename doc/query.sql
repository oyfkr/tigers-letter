CREATE TABLE user_info (
                           id SERIAL PRIMARY KEY,  -- 자동 증가하는 ID
                           email VARCHAR(255) NOT NULL,  -- 이메일 필드는 NULL이 될 수 없음
                           enabled BOOLEAN NOT NULL,  -- enabled 필드는 NULL이 될 수 없음
                           disable_date TIMESTAMP WITH TIME ZONE,  -- nullable 필드, 날짜 및 시간 정보
                           created_at TIMESTAMP WITH TIME ZONE NOT NULL  -- 생성 시간 필드는 NULL이 될 수 없음
);

CREATE TABLE letter (
                        id SERIAL PRIMARY KEY,  -- 자동 증가하는 ID
                        send_date TIMESTAMP WITH TIME ZONE NOT NULL,  -- 발송 날짜, NULL 불가
                        content TEXT NOT NULL,  -- 편지 내용, NULL 불가
                        created_at TIMESTAMP WITH TIME ZONE NOT NULL  -- 생성 시간, NULL 불가
);

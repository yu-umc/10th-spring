package com.example.umc10th.global.security.handler;

@RequiredArgsConstructor
public class OAuthSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtUtil jwtUtil;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException, ServletException {
        // 사전 작업: Response 매핑할 ObjectMapper 선언
        ObjectMapper objectMapper = new ObjectMapper();
        BaseSuccessCode code = MemberSuccessCode.OK;

        // Content-Type, Status 설정
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(code.getStatus().value());

        // 인증 객체 컨테이너에서 OAuth 인증 객체 가져오기
        OAuthMember member = (OAuthMember) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // 토큰 제작을 위해 OAuth 인증 객체에서 Member 추출 -> AuthMember 제작
        String accessToken = jwtUtil.createAccessToken(new AuthMember(member.getMember()));

        // 응답 통일 객체 래핑
        ApiResponse<MemberResDTO.Login> responseBody = ApiResponse.onSuccess(
                code,
                MemberConverter.toLogin(accessToken)
        );

        // 응답 출력
        objectMapper.writeValue(response.getOutputStream(), responseBody);
    }
}
